package edu.gpnu.controller;

import edu.gpnu.api.vo.Result;
import edu.gpnu.entity.User;
import edu.gpnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getUserByStudentId")
    public Result getUserByStudentId(@RequestParam(value = "studentId") String studentId){
        User user = userService.getUserByStudentId(studentId);
        return Result.ok(user);

    }
}
