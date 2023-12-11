package com.song.controller;

import com.song.util.CheckCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/checkCodes")
public class CheckCodesController {
//生成验证码
    @GetMapping
     public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
         //1.使用工具类生成验证码
         //生成4位验证码
      String checkCode = CheckCodeUtil.generateVerifyCode(4);
      //2.将验证码放入session中
     HttpSession session = request.getSession();
        session.setAttribute("checkCode",checkCode);
        //3.将验证码图片响应给客户端
         ServletOutputStream os = response.getOutputStream();//获取输出流
            //返回图片
            CheckCodeUtil.outputImage(100, 40, os, checkCode);//将验证码图片输出到servlet页面
     }
}
