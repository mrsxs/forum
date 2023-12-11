package com.song.domain;

import java.util.List;

/**
 * @author Administrator
 */
public class Post {
    //帖子id
    private Integer id;
   //帖子标题
    private String title;
    //帖子内容
    private String content;
    //用户
    private User user;
    //创建时间
    private String createdAt;
    //点击数
    private int clickCount;
    //修改时间
    private String updatedAt;
    //最后回复时间
    private String lastCommentAt;
    //最后回复用户
    private User lastCommentUser;
    //回复数
    private Integer commentCount;


    public Post() {
    }

    public Post(Integer id, String title, String content, User user, String createdAt, int clickCount, String updatedAt, String lastCommentAt, User lastCommentUser, Integer commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
        this.clickCount = clickCount;
        this.updatedAt = updatedAt;
        this.lastCommentAt = lastCommentAt;
        this.lastCommentUser = lastCommentUser;
        this.commentCount = commentCount;
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     * 获取
     * @return clickCount
     */
    public int getClickCount() {
        return clickCount;
    }

    /**
     * 设置
     * @param clickCount
     */
    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * 获取
     * @return updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置
     * @param updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取
     * @return lastCommentAt
     */
    public String getLastCommentAt() {
        return lastCommentAt;
    }

    /**
     * 设置
     * @param lastCommentAt
     */
    public void setLastCommentAt(String lastCommentAt) {
        this.lastCommentAt = lastCommentAt;
    }

    /**
     * 获取
     * @return lastCommentUser
     */
    public User getLastCommentUser() {
        return lastCommentUser;
    }

    /**
     * 设置
     * @param lastCommentUser
     */
    public void setLastCommentUser(User lastCommentUser) {
        this.lastCommentUser = lastCommentUser;
    }

    /**
     * 获取
     * @return commentCount
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置
     * @param commentCount
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String toString() {
        return "Post{id = " + id + ", title = " + title + ", content = " + content + ", user = " + user + ", createdAt = " + createdAt + ", clickCount = " + clickCount + ", updatedAt = " + updatedAt + ", lastCommentAt = " + lastCommentAt + ", lastCommentUser = " + lastCommentUser + ", commentCount = " + commentCount + "}";
    }
}
