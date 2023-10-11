package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.common.enums.NavMenuActionType;
import com.example.demo.common.enums.NavMenuPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("nav_menu")
public class NavMenu {

    public Long id;
    public String name;
    public String cover;

    public NavMenuActionType actionType;
    public NavMenuPosition position;
    public Integer sort;
    public String description;
    public String open_page;
    public String open_page_param;
    public String open_url;

    @TableField(fill = FieldFill.INSERT)
    public LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public LocalDateTime updatedAt;
}
