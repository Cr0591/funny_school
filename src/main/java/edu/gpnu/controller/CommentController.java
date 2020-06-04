package edu.gpnu.controller;

import edu.gpnu.api.vo.Result;
import edu.gpnu.entity.Comment;
import edu.gpnu.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/getCommentByArticleId")
    public Result getCommentByArticleId(@RequestParam("articleId") String articleId){
        List<Comment> comments = commentService.queryByArticleId(articleId);
        return Result.ok(comments);
    }


}
