package com.song.service.impl;

import com.song.dao.PostDao;
import com.song.domain.Post;
import com.song.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  post业务层实现类
 * @author Administrator
 */
@Service // 添加@Service
public class PostServiceImpl implements PostService {


    @Autowired //添加@Autowired
    private PostDao postDao;
    @Override
    public int addPost(Post post) {
//        添加帖子
        return postDao.addPost(post);
    }

    @Override
    public int deletePost(int id) {
        //删除帖子
        return postDao.deletePost(id);
    }

    @Override
    public int updatePost(Post post) {
        //  修改帖子
        return postDao.updatePost(post);
    }

    @Override
    public List<Post> SelectAll() {
        //查询所有帖子
        return postDao.selectAll();
    }

    @Override
    public Post selectById(int id) {
        //根据id查询帖子
        postDao.updateClickCount(id);
        return postDao.SelectById(id);
    }
}
