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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public CommonResult<Boolean> updateUserInfo(UserInfo userInfo) {
        Boolean result = updateUser(userInfo);
        return CommonResult.success(result, "用户密码更新成功");
    }

    private Boolean updateUser(UserInfo user) {
        loginDao.updateUserInfo(user);
        String password = loginDao.getPassword(user.getUserId());
        ArrayList<String> items = new ArrayList<>();
        items.add("a1");

        // 触发异常
//        String index2 = items.get(2);
        return true;
    }
}
