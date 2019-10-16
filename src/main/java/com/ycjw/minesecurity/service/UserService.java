package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    /**
     *登录
     *@param  account 账号
     *@param  password 密码
     *@return 登录成功用户相关信息
     */
    User login(String account, String password) throws ExceptionZyc;
    /**
     *注册
     *@param account 账号
     * @param password 密码
     * @return 注册成功返回用户相关信息
     */
    User register(String account,String password) throws ExceptionZyc;

    /**
     *  修改用户的手机号
     * @param telephone 旧手机号
     * @param phoneNum 新手机号
     * @return 修改成功的用户信息
     */
    User modify_user_phoneNum(String telephone,String phoneNum) throws ExceptionZyc;

    /**
     *  修改用户的姓名
     * @param telephone 用户的手机号
     * @param name 用户的姓名
     * @return 修改成功的用户信息
     */
    User modify_user_name(String telephone, String name) throws ExceptionZyc;

    /**
     *  修改用户的性别
     * @param telephone  用户的手机号
     * @param sex 用户的性别，1-男，2-女，3-未知
     * @return 修改成功的用户信息
     */
    User modify_user_sex(String telephone, int sex)throws ExceptionZyc;

    /**
     * 用户修改密码
     * @param telephone 手机号
     * @param old_password 旧密码
     * @param new_password 新密码
     * @return 修改后的用户信息
     * @throws ExceptionZyc
     */
    User modifyPassword(String telephone,String old_password,String new_password) throws ExceptionZyc;


    /**
     *  修改用户的头像
     * @param telephone 用户手机号
     * @param headImg 用户头像
     * @return 修改后的用户信息
     * @throws ExceptionZyc
     */
    User modify_user_headImg(String telephone, MultipartFile headImg) throws ExceptionZyc;

    /**
     *  查询用户信息
     * @param telephone 用户手机号
     * @return 用户信息
     * @throws ExceptionZyc
     */
    User getUser(String telephone) throws ExceptionZyc;

    /**
     *  获取用户头像
     * @param telephone 用户手机号
     * @return 用户信息
     * @throws ExceptionZyc
     */
    void getUserHeadImg(String telephone, HttpServletResponse response, HttpServletRequest request) throws ExceptionZyc;
}
