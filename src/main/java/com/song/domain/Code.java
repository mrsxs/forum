package com.song.domain;

public class Code {
    //查询成功
    public static final Integer SELECT_OK = 20011;
    //删除成功
    public static final Integer DELETE_OK = 20021;
    //修改成功
    public static final Integer UPDATE_OK = 20031;
    //添加成功
    public static final Integer ADD_OK = 20041;

    //查询失败
    public static final Integer SELECT_ERROR = 20010;
    //删除失败
    public static final Integer DELETE_ERROR = 20020;
    //修改失败
    public static final Integer UPDATE_ERROR = 20030;
    //添加失败
    public static final Integer ADD_ERROR = 20040;
    //系统错误
    public static final Integer SYSTEM_ERROR = 50001;
    //业务错误
    public static final Integer BUSINESS_ERROR = 60002;
    //系统未知错误
    public static final Integer SYSTEM_UNKNOW_ERROR = 59999;

}
