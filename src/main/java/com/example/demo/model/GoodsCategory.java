package com.example.demo.model;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("goods_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsCategory {
    @TableId(type = IdType.AUTO)
    public Long id;

    public String name;

    public Boolean enabled;

    public Integer sort;

    public Long pid;

    @TableField(fill = FieldFill.INSERT)
    public LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public LocalDateTime updatedAt;
}
