package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("nav_menu_position")
public class NavMenuPosition {
    @TableId(type = IdType.AUTO)
    @TableField
    public Long id;

    public String name;

    public String value;

    public String description;

    public Integer sort;

    @TableField(fill = FieldFill.INSERT)
    public LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public LocalDateTime updatedAt;
}
