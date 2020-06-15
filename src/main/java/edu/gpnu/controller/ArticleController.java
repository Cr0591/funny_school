package edu.gpnu.controller;

import edu.gpnu.api.vo.Result;
import edu.gpnu.entity.Article;
import edu.gpnu.service.IArticleService;
import edu.gpnu.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @GetMapping("/list")
    public Result list(Article article){
        System.out.println("/article/list执行");
        List<ArticleVO> articleVOS = articleService.queryArticleVOs(article);
        return Result.ok(articleVOS);
    }

    @PostMapping("/add")
    public Result add(@RequestBody ArticleVO articleVO){
        System.out.println("文章添加");

        try {
            boolean success = articleService.add(articleVO);
            if(!success){
                return Result.error("发布失败，请重新尝试");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok("发布成功");
    }
}
