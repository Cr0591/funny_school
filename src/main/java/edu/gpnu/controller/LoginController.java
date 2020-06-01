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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public Result login(User user) {
        System.out.println(user);
        if (user == null) {
            return Result.error("登录失败");
        }
        String studentId = user.getStudentId();
        String password = user.getPassword();
        User queryUser = userService.getUsersByStudentId(studentId);
        if (queryUser == null) {
            return Result.error(401, "学号错误");
        }
        if (!queryUser.getPassword().equals(password)) {
            return Result.error(401, "密码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(studentId, password, false);
        try {
            subject.login(usernamePasswordToken);
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return Result.error("密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return Result.error("登录失败");
        }
        return Result.ok("登录成功");
    }

    @PostMapping("/register")
    public Result register(User user){
        int effectedNum = userService.saveUser(user);
        if (effectedNum > 0){
            String studentId = user.getStudentId();
            String password = user.getPassword();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(studentId, password, false);
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);
            return Result.ok("注册成功");
        }else {
            return Result.error("注册失败，请重新尝试");
        }
    }

}
