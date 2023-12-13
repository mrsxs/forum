package com.song.service;
import com.song.domain.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * comment业务层接口类
 * @author Administrator
 */
@Transactional
public interface CommentService {

    /**
     * 添加评论
     * @param comment 评论
     * @return 返回受影响的行数
     */
    int addComments(Comment comment);

    /**
     * 根据帖子id查询评论
     * @param pid 帖子id
     * @return 返回评论集合
     */
    List<Comment> selectByPid(int pid);
}
