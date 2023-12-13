package com.song.service;

import com.song.domain.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * user业务层接口类
 * @author Administrator
 */
@Transactional
public interface UserService {
    /**
     * 添加用户
     * @param user 用户
     */
    int addUser(User user);

    /**
     * 登录
     * @param user 用户
     */
    User login(User user);

    /**
     * 根据id查询用户
     * @param id 用户id
     */
    User selectById(Integer id);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     */
    User selectByUsername(String username);

}
