package com.example.login;

import org.springframework.stereotype.Service;

public interface LoginService {
    void loginCheck(String userId, String password);
}
