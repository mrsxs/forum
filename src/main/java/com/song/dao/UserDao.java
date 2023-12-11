package com.song.dao;

import com.song.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
//TODO 添加@Mapper
@Repository
@Mapper
public interface UserDao {
    /**
     *添加用户
     * @param user 用户
     * @return
     */

    @Insert("insert into users(username, password) values(#{username},#{password})")
    int addUser(User user);

    /**
     * 登录
     * @return
     */
    @Select("select id, username from users where username=#{username} and password=#{password}")
    User login(User user);

    /**
     * 根据id查询用户
     */
    @Select("select * from users where id=#{id}")
    User selectById(int id);

    /**
     * 根据用户名查询用户
     */
    @Select("select * from users where username=#{username}")
    User selectByUsername(String username);
    /**
     * id查询用户
     */
    @Select("select * from users where id=#{id}")
    User selectUserById(int id);
}
