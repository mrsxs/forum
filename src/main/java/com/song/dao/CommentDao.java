package com.song.dao;

import com.song.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
//TODO 添加@Mapper
@Mapper
public interface CommentDao {
    //添加评论
    @Insert("insert into comments(content,uid,pid,created_at) values(#{content},#{user.id},#{pid},#{createdAt})")
    int addComments(Comment comment);
 //查询指定帖子的评论
    List<Comment> selectByPid(int pid);

}
