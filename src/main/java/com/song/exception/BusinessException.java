package com.song.exception;

/**
 * @author Administrator
 */
public class BusinessException  extends  RuntimeException{
    //异常码
    private Integer code;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException( Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
