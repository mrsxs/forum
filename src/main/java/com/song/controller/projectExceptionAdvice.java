package com.song.controller;

import com.song.domain.Code;
import com.song.domain.Result;
import com.song.exception.BusinessException;
import com.song.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Administrator
 */
@RestControllerAdvice
public class projectExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(Exception ex) {
        //记录日志
        //发送消息 通知运维人员
        return new Result(((SystemException) ex).getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result BusinessException(Exception ex) {
        return new Result(((BusinessException) ex).getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        //发送消息 通知运维人员
        return new Result(Code.SYSTEM_UNKNOW_ERROR, null,"系统繁忙，请稍后再试");
    }
}
