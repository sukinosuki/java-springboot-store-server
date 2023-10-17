package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("goods_2_goods_category")
public class Goods2GoodsCategory {

    public Long goodsId;

    public Long goodsCategoryId;
}
