package edu.gpnu.service;

import edu.gpnu.vo.ArticleVO;

import java.util.List;

public interface IArticleService {

    List<ArticleVO> queryArticleVOs();

    boolean add(ArticleVO articleVO);
}
