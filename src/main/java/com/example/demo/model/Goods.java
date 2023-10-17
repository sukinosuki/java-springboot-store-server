package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("goods")
public class Goods {

    @TableId(type = IdType.AUTO)
    public Long id;

    public String name;

    public String description;

    public String detail;

    public Integer sort;

    public String covers;

    public Boolean onShelf;

    public BigDecimal price;

    public Integer stock;

    public Integer fakeBaseSales;

    public Integer realSales;

    @TableField(fill = FieldFill.INSERT)
    public LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public LocalDateTime updatedAt;
}
