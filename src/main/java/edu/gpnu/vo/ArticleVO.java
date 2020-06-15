package edu.gpnu.vo;

import edu.gpnu.entity.Article;
import edu.gpnu.entity.ArticlePicture;
import edu.gpnu.entity.Comment;
import edu.gpnu.entity.User;

import java.io.Serializable;
import java.util.List;

public class ArticleVO implements Serializable {
    private Article article;
    private User user;
    private List<ArticlePicture> articlePictureList;
    private List<CommentVO> commentVOList;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ArticlePicture> getArticlePictureList() {
        return articlePictureList;
    }

    public void setArticlePictureList(List<ArticlePicture> articlePictureList) {
        this.articlePictureList = articlePictureList;
    }

    public List<CommentVO> getCommentVOList() {
        return commentVOList;
    }

    public void setCommentVOList(List<CommentVO> commentVOList) {
        this.commentVOList = commentVOList;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "article=" + article +
                ", articlePictureList=" + articlePictureList +
                '}';
    }
}
