package edu.gpnu.vo;

import edu.gpnu.entity.Article;
import edu.gpnu.entity.ArticlePicture;

import java.io.Serializable;
import java.util.List;

public class ArticleVO implements Serializable {
    private Article article;
    private List<ArticlePicture> articlePictureList;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<ArticlePicture> getArticlePictureList() {
        return articlePictureList;
    }

    public void setArticlePictureList(List<ArticlePicture> articlePictureList) {
        this.articlePictureList = articlePictureList;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "article=" + article +
                ", articlePictureList=" + articlePictureList +
                '}';
    }
}
