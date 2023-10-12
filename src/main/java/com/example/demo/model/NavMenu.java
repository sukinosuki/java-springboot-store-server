package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.example.demo.common.enums.NavMenuActionType;
import com.example.demo.common.enums.NavMenuOpenPage;
import com.example.demo.common.enums.NavMenuPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Accessors(chain = true)
@TableName(value = "nav_menu")
public class NavMenu {
    @TableId(type = IdType.AUTO)
    public Long id;

    public String name;

    public String cover;

//    @TableField(typeHandler = JacksonTypeHandler.class)
    public NavMenuActionType actionType;

    public NavMenuPosition position;

    public Integer sort;

    public String description;

    public NavMenuOpenPage openPage;

    public String openPageParam;

    public String openUrl;

    @TableField(fill = FieldFill.INSERT)
    public LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public LocalDateTime updatedAt;
}
