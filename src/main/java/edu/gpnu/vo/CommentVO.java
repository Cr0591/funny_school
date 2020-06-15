package edu.gpnu.vo;

import edu.gpnu.entity.Comment;
import edu.gpnu.entity.User;

public class CommentVO {
    private Comment comment;
    private User user;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
