package edu.gpnu.service;

import edu.gpnu.entity.Article;
import edu.gpnu.vo.ArticleVO;

import java.util.List;

public interface IArticleService {

    List<ArticleVO> queryArticleVOs(Article article);

    List<ArticleVO> getFollowingArticle(String studentId);

    boolean add(ArticleVO articleVO);
}
