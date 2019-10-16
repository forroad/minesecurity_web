package com.ycjw.minesecurity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 用户类
 */
@Data
@Entity
public class User {

    /**
     * 用户Id
     */
    @Id
    private String userId;

    /**
     * 用户电话
     */
    @NotBlank
    private String phoneNum;

    /**
     * 姓名
     */
    private String userName = "未知";

    /**
     * 性别：1-男，0-女，3-未知
     */
    private int userSex = 3;

    /**
     * 用户密码
     */
    @NotBlank
    private String password;

    /**
     * 用户注册时间
     */
    private Date registerDate;

    /**
     * 是否完善资料
     */
    private boolean isComplete = false;

    /**
     * 用户头像储存路径
     */
    private String headImgPath;

    public boolean CompleteState(){
        if(userName.equals("未知") || userSex == 3){
            return false;
        }
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getHeadImgPath() {
        return headImgPath;
    }

    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
    }
}
