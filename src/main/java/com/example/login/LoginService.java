package com.example.login;

import com.example.common.CommonResult;
import com.example.entity.UserInfo;

public interface LoginService {
    CommonResult loginCheck(String userId, String password);

    CommonResult updateUserInfo(UserInfo userInfo);
}
