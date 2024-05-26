package com.example.login.impl;

import com.example.common.CommonResult;
import com.example.common.ResultCode;
import com.example.dao.LoginDao;
import com.example.entity.UserInfo;
import com.example.entity.UserResponse;
import com.example.login.LoginService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-24 18:46:04
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;

    @Autowired
    UserResponse userResponse;

    @SneakyThrows
    @Override
    public CommonResult<UserInfo> loginCheck(String userId, String password) {
        String pwd = loginDao.getPassword(userId);
        if (password.equals(pwd)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setPassword("***");
            return CommonResult.success(userInfo, "认证成功");
        } else {
            return CommonResult.failed(ResultCode.FORBIDDEN, "认证失败");
        }
    }
}
