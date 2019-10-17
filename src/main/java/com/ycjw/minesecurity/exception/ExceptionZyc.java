package com.ycjw.minesecurity.exception;

import com.ycjw.minesecurity.model.response.Response;

public class ExceptionZyc extends Exception {

    public static ExceptionZyc USERNAME_IS_NOT_TRUE = new ExceptionZyc(new Response("请输入正确的用户名",null));
    public static ExceptionZyc PASSWORD_IS_NOT_TRUE = new ExceptionZyc(new Response("密码不符合规范 ",null));
    public static ExceptionZyc PASSWORD_IS_WRONG = new ExceptionZyc(new Response("密码错误 ",null));
    public static ExceptionZyc OLD_PASSWORD_IS_WRONG = new ExceptionZyc(new Response("输入的旧密码有误 ",null));
    public static final ExceptionZyc USER_IS_NOT_EXIST = new ExceptionZyc(new Response("用户不存在", null));
    public static final ExceptionZyc USER_IS_EXIST = new ExceptionZyc(new Response("用户已存在", null));
    public static final ExceptionZyc TELEPHONE_ALREADY_USE = new ExceptionZyc(new Response("手机号已被使用", null));
    public static final ExceptionZyc PARAM_IS_NULL = new ExceptionZyc(new Response("参数不能为空", null));
    public static final ExceptionZyc IMAGE_IS_NULL = new ExceptionZyc(new Response("上传图片失败，换张图片再试", null));
    public static final ExceptionZyc IMAGE_URL_IS_NULL = new ExceptionZyc(new Response("获取头像失败", null));
    public static final ExceptionZyc FILE_IS_NULL = new ExceptionZyc(new Response("上传文件失败", null));
    public static final ExceptionZyc DOWNLOAD_FAILURE = new ExceptionZyc(new Response("下载文件失败", null));
    public static final ExceptionZyc RESOURCE_IS_NOT_EXIST = new ExceptionZyc(new Response("资源不存在", null));


    private Response response;

    public ExceptionZyc() {
    }

    public ExceptionZyc(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
