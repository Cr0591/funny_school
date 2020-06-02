package edu.gpnu.mapper;

import edu.gpnu.entity.ArticlePicture;

import java.util.List;

public interface ArticlePictureMapper {
    int insert(ArticlePicture articlePicture);

    /**
     * 根据id查询图片相对地址
     * @param articleId
     * @return
     */
    List<ArticlePicture> queryByArticleId(String articleId);
}
