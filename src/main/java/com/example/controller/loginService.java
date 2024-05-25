package com.example.controller;

import com.example.entity.UserInfo;
import com.example.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-24 18:43:15
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class loginService {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public void loginCheck(@RequestBody UserInfo user) {
        loginService.loginCheck(user.getUserId(), user.getPassword());
    }

}
