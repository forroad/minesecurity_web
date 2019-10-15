package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
}
