package edu.gpnu.mapper;

import edu.gpnu.entity.Comment;

import java.util.List;

public interface CommentMapper {

    List<Comment> queryByArticleId(String articleId);
}
