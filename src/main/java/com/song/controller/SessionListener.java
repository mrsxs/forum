package com.song.controller;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Nothing to do here if you're just tracking active sessions
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        List<String> onlineUsers = (List<String>) context.getAttribute("onlineUsers");
        String username = (String) session.getAttribute("username");
        if (username != null) {
            onlineUsers.remove(username);
            context.setAttribute("onlineUsers", onlineUsers);
        }
    }
}
