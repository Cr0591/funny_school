package edu.gpnu.service.impl;

import edu.gpnu.entity.Comment;
import edu.gpnu.mapper.CommentMapper;
import edu.gpnu.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> queryByArticleId(String articleId) {
        return commentMapper.queryByArticleId(articleId);
    }

    @Override
    public int add(Comment comment) {
        return commentMapper.insert(comment);
    }
}
