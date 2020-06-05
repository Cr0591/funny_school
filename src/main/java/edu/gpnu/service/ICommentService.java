package edu.gpnu.service;

import edu.gpnu.entity.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> queryByArticleId(String articleId);

    int add(Comment comment);
}
