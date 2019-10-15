package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.User;

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
}
