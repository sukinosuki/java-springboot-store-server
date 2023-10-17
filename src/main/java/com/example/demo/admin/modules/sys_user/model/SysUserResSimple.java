package com.example.demo.admin.modules.sys_user.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SysUserResSimple {
    public Long id;
    public String username;
    public String avatar;
    public Boolean enabled;
    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public List<Role> roles;

    public static class Role {
        public Long id;

        public String name;
    }
}
