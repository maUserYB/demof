package com.example.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-25 22:23:37
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@Component
public class UserResponse {
    private String code;
    private String message;
    private Object data;
}
