package com.song.service.impl;

import com.song.dao.CommentDao;
import com.song.domain.Comment;
import com.song.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public int addComments(Comment comment) {
        return commentDao.addComments(comment);
    }

    @Override
    public List<Comment> selectByPid(int pid) {
        return commentDao.selectByPid(pid);
    }
}
