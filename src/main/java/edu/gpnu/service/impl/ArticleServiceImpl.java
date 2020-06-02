package edu.gpnu.service.impl;

import edu.gpnu.entity.Article;
import edu.gpnu.entity.ArticlePicture;
import edu.gpnu.entity.User;
import edu.gpnu.mapper.ArticleMapper;
import edu.gpnu.mapper.ArticlePictureMapper;
import edu.gpnu.service.IArticleService;
import edu.gpnu.util.ImageUtil;
import edu.gpnu.util.UUIDGenerator;
import edu.gpnu.vo.ArticleVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticlePictureMapper articlePictureMapper;

    @Override
    public List<ArticleVO> queryArticleVOs() {
        return articleMapper.queryArticleVOs();
    }

    @Override
    @Transactional
    public boolean add(ArticleVO articleVO) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String studentId = user.getStudentId();

        Article article = articleVO.getArticle();
        String articleId = UUIDGenerator.get32UUID();
        article.setId(articleId);
        article.setCreateBy(studentId);
        int effectedNum = articleMapper.insert(article);
        if (effectedNum != 1){
            return false;
        }
        List<ArticlePicture> articlePictureList = articleVO.getArticlePictureList();
        if (articlePictureList != null){
            for (ArticlePicture articlePicture : articlePictureList) {
                articlePicture.setId(UUIDGenerator.get32UUID());
                String relativeAddr = null;
                try {
                    relativeAddr = ImageUtil.writeImg(File.separator + studentId, articlePicture.getPicture());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                articlePicture.setPicture(relativeAddr);
                articlePicture.setArticleId(articleId);
                effectedNum = articlePictureMapper.insert(articlePicture);
                if (effectedNum != 1){
                    return false;
                }
            }
        }
        return true;
    }
}
