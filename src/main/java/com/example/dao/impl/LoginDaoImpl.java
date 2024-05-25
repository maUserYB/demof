package com.example.dao.impl;

import com.example.dao.LoginDao;
import com.example.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-24 19:06:34
 * @Description: TODO
 * @Version: 1.0
 */
@Repository
public class LoginDaoImpl implements LoginDao {
    @Autowired
    LoginMapper loginMapper;
    @Override
    public String getPassword(String userId) {
        String password = loginMapper.getPassword(userId);
        return password;
    }
}
