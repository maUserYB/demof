package com.example.mapper;

import com.example.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-24 19:07:23
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface LoginMapper {
    String getPassword(String userId);

    void updateUser(@Param("user") UserInfo user);
}
