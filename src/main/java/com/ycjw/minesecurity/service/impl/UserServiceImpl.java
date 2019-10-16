package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.User;
import com.ycjw.minesecurity.repository.UserDao;
import com.ycjw.minesecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
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
        verifyPassword(password);
        //根据账号查询数据库
        User user = userDao.findByPhoneNum(telephone);
        //判断用户是否存在
        if(user == null){
            //用r户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        //用户存在，判断密码是否相同
        if(!encode(password,user.getUserId()).equals(user.getPassword())){
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
        verifyPassword(password);
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
        user.setPassword(encode(password,user.getUserId()));
        //设置保存时间
        user.setRegisterDate(new Date(currenttime));
        //保存用户
        userDao.save(user);
        //返回相关信息
        return user;
    }

    @Override
    public User modify_user_phoneNum(String telephone, String phoneNum) throws ExceptionZyc{
        verifyAccount(telephone);
        //根据账号查询数据库
        User user = userDao.findByPhoneNum(telephone);
        //判断用户是否存在
        if(user == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        //判断新手机号是否已被使用
        if(userDao.findByPhoneNum(phoneNum) != null){
            //用户已存在
            throw ExceptionZyc.TELEPHONE_ALREADY_USE;
        }
        //保存用户信息
        user.setPhoneNum(phoneNum);
        user = userDao.save(user);
        //返回用户信息
        return user;
    }

    @Override
    public User modify_user_name(String telephone, String name) throws ExceptionZyc{
        verifyAccount(telephone);
        //根据账号查询数据库
        User user = userDao.findByPhoneNum(telephone);
        //判断用户是否存在
        if(user == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        user.setUserName(name);
        if(user.CompleteState()){
            user.setComplete(true);
        }else {
            user.setComplete(false);
        }
        //保存用户信息
        user = userDao.save(user);
        //返回用户信息
        return user;
    }

    @Override
    public User modify_user_sex(String telephone, int sex)throws ExceptionZyc {
        verifyAccount(telephone);
        //根据账号查询数据库
        User user = userDao.findByPhoneNum(telephone);
        //判断用户是否存在
        if(user == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        //保存用户信息
        user.setUserSex(sex);
        if(user.CompleteState()){
            user.setComplete(true);
        }else {
            user.setComplete(false);
        }
        //返回用户信息
        user = userDao.save(user);
        return user;
    }

    @Override
    public User modifyPassword(String telephone, String old_password, String new_password) throws ExceptionZyc {
        verifyAccount(telephone);
        verifyPassword(new_password);
        //根据账号查询数据库
        User user = userDao.findByPhoneNum(telephone);
        //判断用户是否存在
        if(user == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        //用户存在，判断密码是否相同
        if(!encode(old_password,user.getUserId()).equals(user.getPassword())){
            //密码不同，抛出错误
            throw ExceptionZyc.OLD_PASSWORD_IS_WRONG;
        }
        user.setPassword(encode(new_password,user.getUserId()));
        return userDao.save(user);
    }

    @Override
    public User modify_user_headImg(String telephone, MultipartFile headImg) throws ExceptionZyc {
        verifyAccount(telephone);
        //根据账号查询数据库
        User user = userDao.findByPhoneNum(telephone);
        //判断用户是否存在
        if(user == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        //判断文件是否为空
        if (headImg == null||headImg.isEmpty()) {
            //文件为空
            throw ExceptionZyc.IMAGE_IS_NULL;
        }
        String fileAllName = headImg.getOriginalFilename();
        int index = fileAllName.indexOf(".");
        String imgtype = fileAllName.substring(index,fileAllName.length());
        String path = "C:\\minesecurity\\user\\";
        File fileImage = new File(path);
        if (!fileImage.exists()) {
            fileImage.mkdirs();
        }
        try {
            uploadFile(headImg.getBytes(), path, user.getUserId() + imgtype);
            user.setHeadImgPath(path + user.getUserId() + imgtype);
            user = userDao.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //其他错误
        return user;
    }

    @Override
    public User getUser(String telephone) throws ExceptionZyc {
        verifyAccount(telephone);
        //根据账号查询数据库
        User user = userDao.findByPhoneNum(telephone);
        //判断用户是否存在
        if(user == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        return user;
    }

    @Override
    public void getUserHeadImg(String telephone, HttpServletResponse response, HttpServletRequest request) throws ExceptionZyc {
        verifyAccount(telephone);
        //根据账号查询数据库
        User user = userDao.findByPhoneNum(telephone);
        //判断用户是否存在
        if(user == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        String fileUrl = user.getHeadImgPath();
        if(fileUrl == null || fileUrl.equals("") || fileUrl.length() <= 0){
            //用户不存在，抛出错误
            throw ExceptionZyc.IMAGE_URL_IS_NULL;
        }
        try {
            File file = new File(fileUrl);
            //判断文件是否存在如果不存在就返回默认图标
            if(!(file.exists() && file.canRead())) {
                //用户不存在，抛出错误
                throw ExceptionZyc.IMAGE_URL_IS_NULL;
            }
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            response.setContentType("image/png");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private  void uploadFile(byte[] file, String filePath, String fileName)throws Exception{
        File targetFile=new File(filePath);
        if(targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out =new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 验证手机号是否符合规范
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

    /**
     * 验证密码是否符合规范
     * @param password 需要验证的密码
     * @throws ExceptionZyc 可能产生的错误
     */
    private void verifyPassword(String password) throws ExceptionZyc{
        //正则表达式格式
        String regex = "^[0-9A-Za-z]{6,11}$";
        //验证密码是否符合规范
        if(!password.matches(regex)){
            //密码不符合规范，抛出异常
            throw ExceptionZyc.PASSWORD_IS_NOT_TRUE;
        }
    }

    /**
     * 加密密码
     * @param password 密码
     * @param salt 盐
     * @return 加密后的密码
     */
    private String encode(String password,String salt){
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] result = encoder.encode((password + salt).getBytes());
        return new String(result);
    }
}
