package edu.gpnu.controller;

import edu.gpnu.api.vo.Result;
import edu.gpnu.entity.User;
import edu.gpnu.service.IArticleService;
import edu.gpnu.vo.ArticleVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @GetMapping("/list")
    public Result list(){
        System.out.println("到了test方法");
        List<ArticleVO> articleVOS = articleService.queryArticleVOs();
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
