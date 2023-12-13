package com.song.service.impl;

import com.song.dao.CommentDao;
import com.song.domain.Comment;
import com.song.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * comment业务层实现类
 * @author Administrator
 */
@Service // 添加@Service
public class CommentServiceImpl implements CommentService {

    @Autowired //添加@Autowired
    private CommentDao commentDao;

    @Override  //添加@Override
    public int addComments(Comment comment) {
        //添加评论
        return commentDao.addComments(comment);
    }

    @Override //添加@Override
    public List<Comment> selectByPid(int pid) {
        //根据帖子id查询评论
        return commentDao.selectByPid(pid);
    }
}
