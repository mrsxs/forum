package com.song.controller;

import com.song.domain.Code;
import com.song.domain.Post;
import com.song.domain.Result;
import com.song.domain.User;
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
@RequestMapping("/posts")
public class PostController {


    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Result addPost(@RequestBody Post post) {
        System.out.println(post);
        //获取当前系统时间 格式2020-12-12 00:00:00
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        post.setCreatedAt(format);
        post.setUpdatedAt(format);
        Integer id = post.getUser().getId();
        User user = userService.selectById(id);
        if (user == null) {
            return new Result(Code.ADD_ERROR, 0, "用户不存在");
        }
        int i = postService.addPost(post);
        Integer code = i > 0 ? Code.ADD_OK : Code.ADD_ERROR;
        String msg = i > 0 ? "添加成功" : "添加失败";
        return new Result(code, i, msg);
    }

    @DeleteMapping("/{id}")
    public Result deletePost(@PathVariable int id) {
        int i = postService.deletePost(id);
        Integer code = i > 0 ? Code.DELETE_OK : Code.DELETE_ERROR;
        String msg = i > 0 ? "删除成功" : "删除失败";
        return new Result(code, i, msg);
    }

    @PutMapping("/updatePost")
    public Result updatePost(@RequestBody Post post) {
        int i = postService.updatePost(post);
        Integer code = i > 0 ? Code.UPDATE_OK : Code.UPDATE_ERROR;
        String msg = i > 0 ? "修改成功" : "修改失败";
        return new Result(code, i, msg);
    }

    @GetMapping
    public Result selectAll() {
        List<Post> posts = postService.SelectAll();
        Integer code = posts != null ? Code.SELECT_OK : Code.SELECT_ERROR;
        String msg = posts != null ? "" : "查询失败";
        return new Result(code, posts, msg);
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable int id) {
        Post post = postService.selectById(id);
        Integer code = post != null ? Code.SELECT_OK : Code.SELECT_ERROR;
        String msg = post != null ? "" : "查询失败";
        return new Result(code, post, msg);
    }
}
