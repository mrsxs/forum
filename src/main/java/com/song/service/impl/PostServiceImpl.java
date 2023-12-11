package com.song.service.impl;

import com.song.dao.PostDao;
import com.song.domain.Post;
import com.song.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostDao postDao;
    @Override
    public int addPost(Post post) {

        return postDao.addPost(post);
    }

    @Override
    public int deletePost(int id) {
        return postDao.deletePost(id);
    }

    @Override
    public int updatePost(Post post) {
        return postDao.updatePost(post);
    }

    @Override
    public List<Post> SelectAll() {
        return postDao.selectAll();
    }

    @Override
    public Post selectById(int id) {
        postDao.updateClickCount(id);
        return postDao.SelectById(id);
    }
}
