package com.song.controller;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听session的创建与销毁
 * @author 管理员
 */
@WebListener
public class AppSessionListener implements HttpSessionListener {

    /**
     * 当session创建时触发
     * @param se 包含session信息的HttpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 从session中获取ServletContext
        ServletContext ctx = se.getSession().getServletContext();

        // 从ServletContext中获取当前在线人数
        Integer onlineCount = (Integer) ctx.getAttribute("onlineCount");

        // 如果在线人数为null，设置为1，否则增加1
        if (onlineCount == null) {
            onlineCount = 1;
        } else {
            onlineCount++;
        }

        // 将更新后的在线人数设置回ServletContext
        ctx.setAttribute("onlineCount", onlineCount);
    }

    /**
     * 当session销毁时触发
     * @param se 包含session信息的HttpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 从session中获取ServletContext
        ServletContext ctx = se.getSession().getServletContext();

        // 从ServletContext中获取当前在线人数
        Integer onlineCount = (Integer) ctx.getAttribute("onlineCount");

        // 如果在线人数不为null且大于0，减少1
        if (onlineCount != null && onlineCount > 0) {
            ctx.setAttribute("onlineCount", onlineCount - 1);
        }
    }
}