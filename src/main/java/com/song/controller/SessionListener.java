package com.song.controller;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

/**
 * 监听session的创建与销毁
 * 用于统计在线人数
 * @Author: Song
 */
@WebListener //声明当前类是一个监听器
public class SessionListener implements HttpSessionListener {

    /**
     * 当session创建时触发
     * @param se 包含session信息的HttpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 如果你只是在跟踪活动的会话，那么这里无需执行任何操作
    }

    /**
     * 当session销毁时触发
     * @param se 包含session信息的HttpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession(); //获取session
        ServletContext context = session.getServletContext(); //获取ServletContext
        List<String> onlineUsers = (List<String>) context.getAttribute("onlineUsers"); //获取在线用户列表
        String username = (String) session.getAttribute("username"); //获取用户名
        //从在线用户列表中移除该用户
        if (username != null) {
            onlineUsers.remove(username); //从在线用户列表中移除该用户
            context.setAttribute("onlineUsers", onlineUsers); //更新在线用户列表
        }
    }
}