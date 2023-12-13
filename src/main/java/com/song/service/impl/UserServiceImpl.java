package com.song.service.impl;

import com.song.dao.UserDao;
import com.song.domain.User;
import com.song.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * user业务层实现类
 * @author Administrator
 */
@Service // 添加@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public int addUser(User user) {
        //添加用户
        return userDao.addUser(user);
    }

    @Override
    public User login(User user) {
        //登录
        return userDao.login(user);
    }

    @Override
    public User selectById(Integer id) {
        //根据id查询用户
        return userDao.selectById(id);
    }

    @Override
    public User selectByUsername(String username) {
        //根据用户名查询用户
        return userDao.selectByUsername(username);
    }



}
