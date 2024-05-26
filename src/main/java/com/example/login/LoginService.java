package com.example.login;

import com.example.common.CommonResult;

public interface LoginService {
    CommonResult loginCheck(String userId, String password);
}
