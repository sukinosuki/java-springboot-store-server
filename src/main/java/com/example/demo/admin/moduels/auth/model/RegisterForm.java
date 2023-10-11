package com.example.demo.admin.moduels.auth.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    @NotNull(message = "username不能为空")
    @NotEmpty(message = "username不能为空")
    @Size(min = 4, max = 12, message = "username在4-12字符内")
    String username;

    @NotNull(message = "password不能为空")
    @NotEmpty(message = "password不能为空")
    @Size(min = 6, max = 12, message = "password在6-12字符内")
    String password;
}
