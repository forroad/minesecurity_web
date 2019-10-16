package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.User;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Response login(@RequestParam("telephone") String telephone,
                          @RequestParam("password") String password) throws ExceptionZyc {
        return new Response("登录成功",userService.login(telephone,password));
    }
    @ApiOperation("注册")
    @PostMapping("/register")
    public Response register(@RequestParam("telephone") String telephone,
                             @RequestParam("password") String password) throws ExceptionZyc {
        return new Response("注册成功",userService.register(telephone,password));
    }

    @ApiOperation("修改用户的手机号")
    @PostMapping("/modify_user_phoneNum")
    public Response modify_user_phoneNum(@RequestParam("telephone") String telephone,
                                         @RequestParam("phoneNum") String phoneNum) throws ExceptionZyc{
        return new Response("修改成功",userService.modify_user_phoneNum(telephone,phoneNum));
    }


    @ApiOperation("修改用户的姓名")
    @PostMapping("/modify_user_name")
    public Response modify_user_name(@RequestParam("telephone")String telephone,
                                     @RequestParam("name")String name) throws ExceptionZyc{
        return new Response("修改成功",userService.modify_user_name(telephone,name));
    }


    @ApiOperation("修改用户的性别")
    @PostMapping("/modify_user_sex")
    public Response modify_user_sex(@RequestParam("telephone")String telephone,
                                    @RequestParam("sex")int sex)throws ExceptionZyc{
        return new Response("修改成功",userService.modify_user_sex(telephone,sex));
    }

    @ApiOperation("修改用户的密码")
    @PostMapping("/modify_user_password")
    public Response modify_user_password(@RequestParam("telephone")String telephone,
                                         @RequestParam("old_password")String old_password,
                                         @RequestParam("new_password")String new_password)throws ExceptionZyc{
        return new Response("修改成功",userService.modifyPassword(telephone, old_password, new_password));
    }

    @ApiOperation("修改用户的头像")
    @PostMapping("/modify_user_headImg")
    public Response modify_user_headImg(@RequestParam("telephone")String telephone,
                                        @RequestParam("headImg") MultipartFile headImg)throws ExceptionZyc{
        return new Response("修改成功",userService.modify_user_headImg(telephone,headImg));
    }

    @ApiOperation("获取用户的头像")
    @GetMapping(value = "getUserHeadImg",produces = MediaType.IMAGE_JPEG_VALUE)
    public void getUserHeadImg(@RequestParam("telephone")String telephone, HttpServletResponse response, HttpServletRequest request) throws ExceptionZyc{
        userService.getUserHeadImg(telephone,response,request);
    }

    @ApiOperation("获取用户的头像")
    @GetMapping("getUser")
    public Response getUser(@RequestParam("telephone")String telephone) throws ExceptionZyc{
        return new Response("查询成功",userService.getUser(telephone));
    }

}
