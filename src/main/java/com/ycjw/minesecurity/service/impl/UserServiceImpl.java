package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.User;
import com.ycjw.minesecurity.repository.UserDao;
import com.ycjw.minesecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private HttpSession session;
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(HttpSession session, UserDao userDao) {
        this.session = session;
        this.userDao = userDao;
    }

    @Override
    public User login(String telephone, String password) throws ExceptionZyc {
        verifyAccount(telephone);
        //验证密码是否符合规范
        if(StringUtils.isEmpty(password)||password.length() < 6){
            //密码不符合规范，抛出异常
            throw ExceptionZyc.PASSWORD_IS_WRONG;
        }
        //根据账号查询数据库
        User user = userDao.findByPhoneNum(telephone);
        //判断用户是否存在
        if(user == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        //用户存在，判断密码是否相同
        if(!password.equals(user.getPassword())){
            //密码不同，抛出错误
            throw ExceptionZyc.PASSWORD_IS_WRONG;
        }
        //信息符合，执行登录操作,将用户id,角色存入会话中
        session.setAttribute("id",user.getPhoneNum());
        //返回数据
        return user;
    }

    @Override
    public User register(String telephone, String password) throws ExceptionZyc {
        verifyAccount(telephone);
        //验证密码是否符合规范
        if(StringUtils.isEmpty(password)||password.length() < 6){
            //密码不符合规范，抛出异常
            throw ExceptionZyc.PASSWORD_IS_NOT_TRUE;
        }
        //判断用户是否已存在
        if(userDao.findByPhoneNum(telephone) != null){
            //用户已存在
            throw ExceptionZyc.USER_IS_EXIST;
        }
        //信息符合规范，新建用户
        User user = new User();
        long currenttime = System.currentTimeMillis();
        user.setUserId(System.currentTimeMillis() + "" + telephone);
        //设置账号currenttime
        user.setPhoneNum(telephone);
        //设置密码
        user.setPassword(password);
        //设置保存时间
        user.setRegisterDate(new Date(currenttime));
        //保存用户
        userDao.save(user);
        //返回相关信息
        return user;
    }

    /**
     * 验证密码是否符合规范
     * @param telephone 需要验证的密码
     * @throws ExceptionZyc 可能产生的错误
     */
    private void verifyAccount(String telephone) throws ExceptionZyc{
        //正则表达式格式，表示账号为手机号
        String regex = "^[0-9]{11}$";
        //验证账号是否符合规范
        if(!telephone.matches(regex)){
            //账号不符合规范，抛出异常
            throw ExceptionZyc.USERNAME_IS_NOT_TRUE;
        }
    }
}
