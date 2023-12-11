package com.song.service;
import com.song.domain.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 */
@Transactional
public interface CommentService {

    int addComments(Comment comment);

     //查询指定帖子的评论
    List<Comment> selectByPid(int pid);
}
