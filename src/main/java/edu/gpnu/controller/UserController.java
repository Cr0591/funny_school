package edu.gpnu.controller;

import edu.gpnu.api.vo.Result;
import edu.gpnu.entity.User;
import edu.gpnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/edit")
    public Result edit(@RequestBody User user){
        int effectedNum = userService.edit(user);
        if (effectedNum > 0){
            return Result.ok("编辑成功");
        }
        return Result.error("编辑失败");
    }

}
