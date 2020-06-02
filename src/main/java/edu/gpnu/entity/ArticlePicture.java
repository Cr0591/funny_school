package edu.gpnu.entity;

import java.io.Serializable;

public class ArticlePicture implements Serializable {
    private String id;
    private String articleId;
    private String picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "ArticlePicture{" +
                "id='" + id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
