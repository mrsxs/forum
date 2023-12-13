package com.song.controller;

import com.song.domain.Code;
import com.song.domain.Result;
import com.song.domain.User;
import com.song.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result addUser(@RequestBody User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        String code = user.getCode();
        //获取验证码 从session中获取
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        if (!checkCode.equalsIgnoreCase(code)) {
            return new Result(Code.ADD_ERROR, null, "验证码错误");
        }

        if (username == null || password == null) {
            return new Result(Code.ADD_ERROR, "用户名或密码不能为空");
        }
        User user1 = userService.selectByUsername(username);
        if (user1 != null) {
            return new Result(Code.ADD_ERROR, null, "用户名已存在");
        }
        int i = userService.addUser(user);
        return new Result(i > 0 ? Code.ADD_OK : Code.ADD_ERROR, "添加成功");
    }

    //登录 接收验证码

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request) {

        //获取验证码 从session中获取
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        //获取用户输入的验证码
        String code1 = user.getCode();
        //判断验证码是否正确
        if (!checkCode.equalsIgnoreCase(code1)) {
            return new Result(Code.ADD_ERROR, null, "验证码错误");
        }
        String username = user.getUsername();
        String password = user.getPassword();
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return new Result(Code.ADD_ERROR, null, "用户和密码不能为空");
        }
        User login = userService.login(user);
        List<String> onlineUsers = null;
        if (login != null) {
            // 登录成功，将用户信息存储在会话中
            session.setAttribute("User", login);
            //将用户添加到在线用户列表
            ServletContext context = request.getServletContext();
            //获取在线用户列表
            onlineUsers = (List<String>) context.getAttribute("onlineUsers");
            // 如果为空，创建一个
            if (onlineUsers == null) {
                onlineUsers = new ArrayList<>();
            }
            //如果当前用户已经在列表中，不再添加
            if (onlineUsers.contains(username)) {
                request.setAttribute("msg", "用户已登录");
            } else {
                //将当前用户添加到在线用户列表
                onlineUsers.add(username);
                //将在线用户列表添加到application域中
                context.setAttribute("onlineUsers", onlineUsers);
            }
        }
        Integer code = login != null ? Code.SELECT_OK : Code.SELECT_ERROR;
        String msg = login != null ? "" : "用户名或密码错误";
        return new Result(code, onlineUsers, msg);
    }

    @DeleteMapping("/{id}")
    public Result selectById(@PathVariable int id) {
        User user = userService.selectById(id);
        Integer code = user != null ? Code.DELETE_OK : Code.DELETE_ERROR;
        String msg = user != null ? "" : "删除失败";
        return new Result(code, user, msg);
    }

    @GetMapping("/{username}")
    public Result selectByUsername(@PathVariable String username) {
        User user = userService.selectByUsername(username);
        Integer code = user != null ? Code.SELECT_OK : Code.SELECT_ERROR;
        String msg = user != null ? "" : "查询失败";
        return new Result(code, user, msg);
    }
    //获取在线用户列表
    @GetMapping("/onlineUsers")
    public Result onlineUsers(HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        List<String> onlineUsers = (List<String>) context.getAttribute("onlineUsers");
        return new Result(Code.SELECT_OK, onlineUsers, "查询成功");
    }

    //注销
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("User");
        System.out.println(user);
        //获取application域中的在线用户列表
        ServletContext context = request.getServletContext();
        List<String> onlineUsers = (List<String>) context.getAttribute("onlineUsers");
        //同步代码块，确保线程安全
        synchronized (this) { // 或者使用其他的锁对象，例如专门的锁对象或者 onlineUsers 对象自身
            // 如果不为空，删除当前用户
            if (onlineUsers != null && user != null) {
                //删除当前用户
                onlineUsers.remove(user.getUsername());
                //更新ServletContext中的在线用户列表
                context.setAttribute("onlineUsers", onlineUsers);
            }
        }
        session.removeAttribute("User");
        return new Result(Code.SELECT_OK, onlineUsers, "注销成功");
    }

    //判断是否登录
    @GetMapping("/isLogin")
    public Result isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object loggedInUser = session.getAttribute("User");
        if (loggedInUser != null) {
            return new Result(Code.SELECT_OK, loggedInUser, "已登录");
        } else {
            return new Result(Code.SELECT_ERROR, null, "未登录");
        }
    }
}
