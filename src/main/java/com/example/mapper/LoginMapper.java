package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-24 19:07:23
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface LoginMapper {
    String getPassword(String userId);
}
