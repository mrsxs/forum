package com.song.domain;

/**
 * @author Administrator
 */
public class User {
    //用户id
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;

    private String code;

    public User() {
    }

    public User(Integer id, String username, String password, String code) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.code = code;
    }

    /**
     * 获取
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", password = " + password + ", code = " + code + "}";
    }
}
