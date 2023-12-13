package com.song.service;

import com.song.domain.Post;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * post业务层接口类
 * @author Administrator
 */
@Transactional
public interface PostService {

    /**
     *  添加帖子
     * @param post 帖子
     * @return 返回受影响的行数
     */
    int addPost(Post post);

    /**
     * 删除帖子
     * @param id 帖子id
     * @return 返回受影响的行数
     */
    int deletePost(int id);

    /**
     * 修改帖子
     * @param post
     * @return 返回受影响的行数
     */

    int updatePost(Post post);


    /**
     * 查询所有帖子
     * @return 返回帖子集合
     */
    List<Post> SelectAll();

    /**
     * 根据id查询帖子
     * @param id
     * @return 返回帖子
     */
    //开启事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Post selectById(int id);


}
