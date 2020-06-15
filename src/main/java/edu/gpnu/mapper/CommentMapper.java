package edu.gpnu.mapper;

import edu.gpnu.entity.Comment;
import edu.gpnu.vo.CommentVO;

import java.util.List;

public interface CommentMapper {

    List<Comment> queryByArticleId(String articleId);

    List<CommentVO> queryCommentVOByArticleId(String articleId);

    int insert(Comment comment);
}
