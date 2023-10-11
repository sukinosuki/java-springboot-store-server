package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("sys_user")
@Data
public class SysUser {
    @TableId(type = IdType.AUTO)
    Long id;

    String username;
    String password;
    String avatar;
    Boolean enabled;
    String nickname;

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime updatedAt;
}
