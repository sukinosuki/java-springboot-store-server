package com.example.demo.admin.moduels.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRes {
    LoginResUser user;
    String token;
}

