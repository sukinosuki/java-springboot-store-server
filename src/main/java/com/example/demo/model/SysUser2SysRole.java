package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("sys_user_2_sys_role")
public class SysUser2SysRole {

    public Long uid;

    public Long roleId;
}
