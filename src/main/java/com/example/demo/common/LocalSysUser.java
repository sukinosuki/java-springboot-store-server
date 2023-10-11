package com.example.demo.common;

import com.example.demo.model.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocalSysUser {
    Long id;
    SysUser user;
    Exception exception;

    public Boolean isAuthorized() {
        return id != null && id != 0;
    }
}
