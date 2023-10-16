package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("sys_role_2_sys_permission")
public class SysRole2SysPermission {

    public Long roleId;
    public Long permissionId;
}
