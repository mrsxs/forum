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
 * @author Administrator
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping
    public Result addComments(@RequestBody Comment comment) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        comment.setCreatedAt(format);
        System.out.println(comment);
        int uid = comment.getUser().getId();
        int pid = comment.getPid();
        User user = userService.selectById(uid);
        if (user == null) {
            return new Result(Code.ADD_ERROR, 0, "用户不存在");
        }
        Post post = postService.selectById(pid);
        if (post == null) {
            return new Result(Code.ADD_ERROR, 0, "帖子不存在");
        }
        System.out.println(comment);
        int i = commentService.addComments(comment);
        Integer code =i > 0 ? Code.ADD_OK : Code.ADD_ERROR;
        String msg = i>0 ? "添加成功" : "添加失败";
        return new Result(code, i, msg);
    }


    @GetMapping("{id}")
    public Result selectByPid(@PathVariable int id) {
        System.out.println(id);
        List<Comment> comments = commentService.selectByPid(id);
        Integer code = comments != null ? Code.SELECT_OK : Code.SELECT_ERROR;
        String msg = comments != null ? "" : "查询失败";
        return new Result(code, comments, msg);
    }
}
