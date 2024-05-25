package com.example.login.impl;

import com.example.dao.LoginDao;
import com.example.dao.impl.LoginDaoImpl;
import com.example.login.LoginService;
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

    @Override
    public void loginCheck(String userId, String password) {
        String pwd = loginDao.getPassword(userId);
        if (password.equals(pwd)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
