package com.example.demo.admin.moduels.auth;

import com.example.demo.admin.moduels.auth.model.LoginRes;
import com.example.demo.admin.moduels.auth.model.RegisterForm;

public interface AuthService {

    LoginRes register(RegisterForm form);

    LoginRes login(RegisterForm form);

    LoginRes auth(Long uid);
}
