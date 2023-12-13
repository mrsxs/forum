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
 * 帖子控制器
 * @author 管理员
 */
@RestController //声明当前类是一个控制器
@RequestMapping("/posts") //映射请求路径
public class PostController {

    @Autowired
    private PostService postService; //注入帖子服务
    @Autowired
    private UserService userService; //注入用户服务

    /**
     * 添加帖子
     * @param post 帖子对象
     * @return 返回操作结果
     */
    @PostMapping
    public Result addPost(@RequestBody Post post) {
        System.out.println(post);
        //获取当前系统时间 格式2020-12-12 00:00:00
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        post.setCreatedAt(format); //设置帖子创建时间
        post.setUpdatedAt(format); //设置帖子更新时间
        Integer id = post.getUser().getId();
        User user = userService.selectById(id); //查询用户
        //判断用户是否存在
        if (user == null) {
            return new Result(Code.ADD_ERROR, 0, "用户不存在"); //用户不存在返回错误
        }
        int i = postService.addPost(post); //添加帖子
        Integer code = i > 0 ? Code.ADD_OK : Code.ADD_ERROR;
        String msg = i > 0 ? "添加成功" : "添加失败";
        return new Result(code, i, msg); //返回操作结果
    }

    /**
     * 删除帖子
     * @param id 帖子id
     * @return 返回操作结果
     */
    @DeleteMapping("/{id}")
    public Result deletePost(@PathVariable int id) {
        int i = postService.deletePost(id); //删除帖子
        Integer code = i > 0 ? Code.DELETE_OK : Code.DELETE_ERROR;
        String msg = i > 0 ? "删除成功" : "删除失败";
        return new Result(code, i, msg); //返回操作结果
    }

    /**
     * 更新帖子
     * @param post 帖子对象
     * @return 返回操作结果
     */
    @PutMapping("/updatePost")
    public Result updatePost(@RequestBody Post post) {
        int i = postService.updatePost(post); //更新帖子
        Integer code = i > 0 ? Code.UPDATE_OK : Code.UPDATE_ERROR;
        String msg = i > 0 ? "修改成功" : "修改失败";
        return new Result(code, i, msg); //返回操作结果
    }

    /**
     * 查询所有帖子
     * @return 返回操作结果
     */
    @GetMapping
    public Result selectAll() {
        List<Post> posts = postService.SelectAll(); //查询所有帖子
        Integer code = posts != null ? Code.SELECT_OK : Code.SELECT_ERROR;
        String msg = posts != null ? "" : "查询失败";
        return new Result(code, posts, msg); //返回操作结果
    }

    /**
     * 根据id查询帖子
     * @param id 帖子id
     * @return 返回操作结果
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable int id) {
        Post post = postService.selectById(id); //查询帖子
        Integer code = post != null ? Code.SELECT_OK : Code.SELECT_ERROR;
        String msg = post != null ? "" : "查询失败";
        return new Result(code, post, msg); //返回操作结果
    }
}