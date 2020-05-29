package edu.gpnu.controller;

import edu.gpnu.api.vo.Result;
import edu.gpnu.entity.User;
import edu.gpnu.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Result login(User user){
        if (user == null){
            return Result.error("登录失败");
        }
        String studentId = user.getStudentId();
        String password = user.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(studentId,password,false);
        try {
            subject.login(usernamePasswordToken);
        } catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return Result.error("密码错误");
        }catch (AuthenticationException e) {
            e.printStackTrace();
            return Result.error("登录失败");
        }
        return Result.ok("登录成功");
    }
}
