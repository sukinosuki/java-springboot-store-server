package com.example.demo.admin.modules.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResUser {
    Long id;
    String username;
    String nickname;
    String avatar;
    Boolean enabled;
}
