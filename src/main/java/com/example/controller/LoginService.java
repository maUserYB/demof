package com.example.controller;

import com.example.common.CommonResult;
import com.example.entity.UserInfo;
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
public class LoginService {
    @Autowired
    com.example.login.LoginService loginService;

    @PostMapping("/login")
    public CommonResult loginCheck(@RequestBody UserInfo user) {
        return loginService.loginCheck(user.getUserId(), user.getPassword());
    }

    @PostMapping("/updatePwd")
    public CommonResult updateUserInfo(@RequestBody UserInfo userInfo) {
        return loginService.updateUserInfo(userInfo);
    }

}
