package com.song.controller;

import com.song.domain.*;
import com.song.service.CommentService;
import com.song.service.PostService;
import com.song.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 评论控制器
 * @author 管理员
 */
@RestController //声明当前类是一个控制器
@RequestMapping("/comments") //映射请求路径
public class CommentController {

    @Autowired
    private CommentService commentService; //注入评论服务
    @Autowired
    private UserService userService; //注入用户服务
    @Autowired
    private PostService postService; //注入帖子服务

    /**
     * 添加评论
     * @param comment 评论对象
     * @return 返回操作结果
     */
    @PostMapping
    public Result addComments(@RequestBody Comment comment) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        comment.setCreatedAt(format); //设置评论创建时间
        int uid = comment.getUser().getId();
        int pid = comment.getPid();
        User user = userService.selectById(uid); //查询用户
        if (user == null) {
            return new Result(Code.ADD_ERROR, 0, "用户不存在"); //用户不存在返回错误
        }
        Post post = postService.selectById(pid); //查询帖子
        if (post == null) {
            return new Result(Code.ADD_ERROR, 0, "帖子不存在"); //帖子不存在返回错误
        }
        System.out.println(comment);
        int i = commentService.addComments(comment); //添加评论
        Integer code =i > 0 ? Code.ADD_OK : Code.ADD_ERROR;
        String msg = i>0 ? "添加成功" : "添加失败";
        return new Result(code, i, msg); //返回操作结果
    }

    /**
     * 根据帖子id查询评论
     * @param id 帖子id
     * @return 返回操作结果
     */
    @GetMapping("{id}")
    public Result selectByPid(@PathVariable int id) {
        List<Comment> comments = commentService.selectByPid(id); //查询评论
        Integer code = comments != null ? Code.SELECT_OK : Code.SELECT_ERROR;
        String msg = comments != null ? "" : "查询失败";
        return new Result(code, comments, msg); //返回操作结果
    }
}