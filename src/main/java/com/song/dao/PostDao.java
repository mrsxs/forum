package com.song.dao;

import com.song.domain.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Administrator
 */
//TODO 添加@Mapper
@Mapper
public interface PostDao  {

    //添加帖子
    @Insert("insert into posts(title, content, uid,clickCount, created_at, updated_at) values(#{title},#{content},#{user.id},#{clickCount},#{createdAt},#{updatedAt})")
    int addPost(Post post);

    //删除帖子
    @Delete("delete from posts where id=#{id}")
    int deletePost(int id);

    //修改帖子
    @Update("update posts set title=#{title},content=#{content},updated_at=#{updatedAt} where id=#{id}")
    int updatePost(Post post);

    //查询所有


    //添加映射

    List<Post> selectAll();

    //根据id查询

    Post SelectById(int id);
    //增加点击数
    @Update("update posts set clickCount=clickCount+1 where id=#{id}")
    int updateClickCount(int id);
}
