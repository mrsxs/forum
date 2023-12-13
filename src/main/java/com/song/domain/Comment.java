package com.song.domain;

/**
 * @author Administrator
 */
public class Comment {
    //评论id
    private Integer id;
    //评论内容
    private String content;
    //评论用户
    private User user;
    //评论帖子
    private int pid;
    //评论时间
    private String createdAt;
    

    public Comment() {
    }

    public Comment(Integer id, String content, User user, int pid, String createdAt) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.pid = pid;
        this.createdAt = createdAt;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * 设置
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取
     * @return pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * 设置
     * @param pid
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * 获取
     * @return createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置
     * @param createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String toString() {
        return "Comment{id = " + id + ", content = " + content + ", user = " + user + ", pid = " + pid + ", createdAt = " + createdAt + "}";
    }
}
