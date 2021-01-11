package com.six.dao;

import com.six.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    List<Comment> findByVid(Integer vid);

    void addComment(Comment comment);

    void deleteComment(Integer cid);
}
