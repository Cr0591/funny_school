package edu.gpnu.controller;

import edu.gpnu.api.vo.Result;
import edu.gpnu.entity.Follow;
import edu.gpnu.entity.User;
import edu.gpnu.service.IFollowService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/follow")
public class FollowController {

    @Autowired
    private IFollowService followService;

    /**
     * 当前登录的用户 去关注别人
     * 例 a关注b    参数id为b的studentId
     * @param id 被关注的人的studentId
     * @return
     */
    @GetMapping("/follow")
    public Result follow(String id){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Follow follow = new Follow(id,user.getStudentId());
        int effectedNum = followService.add(follow);
        if (effectedNum > 0){
            return Result.ok("关注成功");
        }
        return Result.ok("关注失败，请重新尝试");
    }
}
