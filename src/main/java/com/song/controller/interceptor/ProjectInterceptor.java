package com.song.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@Component
public class ProjectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        //获取请求的url
        String url = request.getRequestURI();
        //如果是登录请求，直接放行
          if (url.contains("login")) {
                return true;
          }
        if (url.contains("add")) {
            return true;
        }
          if ("get".equalsIgnoreCase(method)) {
            return true;
        } else {
            //判断有没有session
            Object loggedInUser = request.getSession().getAttribute("User");
            if (loggedInUser != null) {
                return true;
            } else {
                return false;
            }
        }
    }

}
