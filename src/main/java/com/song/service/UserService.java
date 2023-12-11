package com.song.service;

import com.song.domain.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 */
@Transactional
public interface UserService {
    /**
     * 添加用户
     */
    int addUser(User user);

    /**
     * 登录
     */
    User login(User user);

    /**
     * 根据id查询用户
     */
    User selectById(Integer id);

    /**
     * 根据用户名查询用户
     */
    User selectByUsername(String username);

}
