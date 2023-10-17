package com.example.demo.admin.modules.auth;

import com.example.demo.admin.modules.auth.model.LoginRes;
import com.example.demo.admin.modules.auth.model.RegisterForm;

public interface AuthService {

    LoginRes register(RegisterForm form);

    LoginRes login(RegisterForm form);

    LoginRes auth(Long uid);
}
