package edu.gpnu.shiro;

import edu.gpnu.common.CommonConstant;
import edu.gpnu.entity.User;
import edu.gpnu.service.IUserService;
import edu.gpnu.util.JwtUtil;
import edu.gpnu.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;

    @Autowired
    @Lazy
    private RedisUtil redisUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        //给当前用户授权url为hello的权限码
        simpleAuthorInfo.addStringPermission("how_are_you");
        System.out.println("经试验：并不是每次调用接口就会执行，而是调用需要操作码（permission）的接口就会执行");
        return simpleAuthorInfo;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        String token = (String) authcToken.getCredentials();
        if (token == null) {
            throw new AuthenticationException("token为空!");
        }
        // 校验token有效性
        User user = this.checkUserTokenIsEffect(token);
        return new SimpleAuthenticationInfo(user, token, getName());
    }

    /**
     * 校验token的有效性
     *
     * @param token
     */
    public User checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        String studentId = JwtUtil.getStudentId(token);
        if (studentId == null) {
            throw new AuthenticationException("未登录，请先登录!");
        }

        // 查询用户信息
        System.out.println("———校验token是否有效————checkUserTokenIsEffect——————— "+ token);

        User user = userService.getUserByStudentId(studentId);
        if (user == null) {
            throw new AuthenticationException("用户不存在!");
        }
        // 校验token是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(token, studentId, user.getPassword())) {
            throw new AuthenticationException("Token失效，请重新登录!");
        }

        return user;
    }

    /**
     * JWTToken刷新生命周期 （实现： 用户在线操作不掉线功能）
     * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)，缓存有效期设置为Jwt有效时间的2倍
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     * 4、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 注意： 前端请求Header中设置Authorization保持不变，校验有效性以缓存中的token为准。
     *       用户过期时间 = Jwt有效时间 * 2。
     *
     * @param studentId
     * @param password
     * @return
     */
    public boolean jwtTokenRefresh(String token, String studentId, String password) {
        try {
            redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        } catch (PoolException e) {
            e.printStackTrace();
            throw new RuntimeException("redis连接异常");
        }
        String cacheToken = String.valueOf(redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token));
        if (!StringUtils.isEmpty(cacheToken)) {
            // 校验token有效性
            if (!JwtUtil.verify(cacheToken, studentId, password)) {
                String newAuthorization = JwtUtil.sign(studentId, password);
                // 设置超时时间
                redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, newAuthorization);
                redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME *2 / 1000);
                System.out.println("——————————用户在线操作，更新token保证不掉线—————————jwtTokenRefresh——————— "+ token);
            }
            //update-begin--Author:scott  Date:20191005  for：解决每次请求，都重写redis中 token缓存问题
//			else {
//				// 设置超时时间
//				redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, cacheToken);
//				redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
//			}
            //update-end--Author:scott  Date:20191005   for：解决每次请求，都重写redis中 token缓存问题
            return true;
        }
        return false;
    }
}
