package com.song.service;

import com.song.domain.Post;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 */
@Transactional
public interface PostService {

    /**
     *  添加帖子
     * @param post
     * @return
     */
    int addPost(Post post);

    /**
     * 删除帖子
     * @param id
     * @return
     */
    int deletePost(int id);

    /**
     * 修改帖子
     * @param post
     * @return
     */

    int updatePost(Post post);


    /**
     * 查询所有帖子
     * @return
     */
    List<Post> SelectAll();

    /**
     * 根据id查询帖子
     * @param id
     * @return
     */
    //开启事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Post selectById(int id);


}
