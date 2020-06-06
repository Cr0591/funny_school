package edu.gpnu.controller;

import com.alibaba.fastjson.JSONObject;
import edu.gpnu.api.vo.Result;
import edu.gpnu.common.CommonConstant;
import edu.gpnu.entity.User;
import edu.gpnu.service.IUserService;
import edu.gpnu.util.JwtUtil;
import edu.gpnu.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys")
public class LoginController {
    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;


    @GetMapping("/login")
    public Result login(User user) {
        System.out.println(user);
        if (user == null) {
            return Result.error("登录失败");
        }
        String studentId = user.getStudentId();
        String password = user.getPassword();
        User queryUser = userService.getUserByStudentId(studentId);
        if (queryUser == null) {
            return Result.error(401, "学号错误");
        }
        if (!queryUser.getPassword().equals(password)) {
            return Result.error(401, "密码错误");
        }

        Result<JSONObject> result = new Result<>();

        userInfo(queryUser,result);

        return result;
    }


    /**
     * 用户信息
     *
     * @param user
     * @param result
     * @return
     */
    private Result<JSONObject> userInfo(User user, Result<JSONObject> result) {
        String syspassword = user.getPassword();
        user.setPassword(null);
        String studentId = user.getStudentId();

        // 生成token
        String token = JwtUtil.sign(studentId, syspassword);
        // 设置token缓存有效时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME*2 / 1000);

        // 获取用户部门信息
        JSONObject obj = new JSONObject();
        obj.put("token",token);
        obj.put("userInfo", user);
        result.setResult(obj);
        result.success("登录成功");
        return result;
    }


    @PostMapping("/register")
    public Result register(@RequestBody User user){
        int effectedNum = userService.saveUser(user);
        if (effectedNum > 0){
            return Result.ok("注册成功");
        }else {
            return Result.error("注册失败，请重新尝试");
        }
    }

    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.ok("注销成功");
    }

}
