package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.common.enums.SysPermissionMethod;
import com.example.demo.common.enums.SysPermissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_permission")
public class SysPermission {

    @TableId(type = IdType.AUTO)
    public Long id;

    public String name;

    public String description;

    public Boolean enabled;

    public SysPermissionMethod method;

    public String url;

    public Long pid;

    public SysPermissionType type;

    // TIP: mybatis plus 的service save方法自动插入字段 @TableField是必需的
    @TableField(fill = FieldFill.INSERT)
    public LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public LocalDateTime updatedAt;
}
