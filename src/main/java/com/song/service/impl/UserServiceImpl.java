package com.song.service.impl;

import com.song.dao.UserDao;
import com.song.domain.User;
import com.song.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public User selectById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }



}
