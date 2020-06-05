package edu.gpnu.controller;

import edu.gpnu.api.vo.Result;
import edu.gpnu.entity.Comment;
import edu.gpnu.entity.User;
import edu.gpnu.service.ICommentService;
import edu.gpnu.util.UUIDGenerator;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/getCommentByArticleId")
    public Result getCommentByArticleId(@RequestParam("articleId") String articleId) {
        List<Comment> comments = commentService.queryByArticleId(articleId);
        return Result.ok(comments);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {

        comment.setId(UUIDGenerator.get32UUID());
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        comment.setCreateBy(user.getStudentId());
        int effectedNum = commentService.add(comment);
        if (effectedNum > 0) {
            return Result.ok("评论成功");
        }
        return Result.error("评论失败");
    }


}
