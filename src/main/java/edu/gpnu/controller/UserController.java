package edu.gpnu.controller;

import edu.gpnu.api.vo.Result;
import edu.gpnu.entity.User;
import edu.gpnu.service.IUserService;
import edu.gpnu.util.ImageUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getUserByStudentId")
    public Result getUserByStudentId(@RequestParam(value = "studentId") String studentId){
        User user = userService.getUserByStudentId(studentId);
        user.setPassword(null);
        return Result.ok(user);
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody User user){
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        user.setStudentId(loginUser.getStudentId());

        if (user.getAvatar() != null && !"".equals(user.getAvatar())){
            try {
                String relativeAddr = ImageUtil.writeImg(File.separator + loginUser.getStudentId(), user.getAvatar());
                user.setAvatar(relativeAddr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        int effectedNum = userService.edit(user);
        if (effectedNum > 0){
            return Result.ok("编辑成功");
        }
        return Result.error("编辑失败");
    }

    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(String img){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String studentId = user.getStudentId();
        try {
            String relativeAddr = ImageUtil.writeImg(File.separator + studentId, img);
            User userByStudentId = userService.getUserByStudentId(studentId);
            userByStudentId.setAvatar(relativeAddr);
            int effectedNum = userService.edit(userByStudentId);

            if (effectedNum > 0){
                return Result.ok("编辑成功");
            }
            return Result.error("编辑失败");

        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("头像上传失败，请重新尝试");
        }
    }

}
