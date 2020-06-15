package edu.gpnu.mapper;

import edu.gpnu.entity.Article;
import edu.gpnu.vo.ArticleVO;

import java.util.List;

public interface ArticleMapper {
    int insert(Article article);

    List<ArticleVO> queryArticleVOs(Article article);
}
