package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("sys_user")
@Data
public class SysUser {
    @TableId(type = IdType.AUTO)
    public Long id;

    public String username;

    public String password;

    public String avatar;

    public Boolean enabled;

    public String nickname;

    @TableField(fill = FieldFill.INSERT)
    public LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public LocalDateTime updatedAt;
}
