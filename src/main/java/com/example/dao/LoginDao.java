package com.example.dao;

import org.springframework.stereotype.Repository;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-24 19:03:10
 * @Description: TODO
 * @Version: 1.0
 */
public interface LoginDao {
    String getPassword(String userId);
}
