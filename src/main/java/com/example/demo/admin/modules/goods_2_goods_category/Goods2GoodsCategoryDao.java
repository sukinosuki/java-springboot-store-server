package com.example.demo.admin.modules.goods_2_goods_category;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.Goods2GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Goods2GoodsCategoryDao extends ServiceImpl<Goods2GoodsCategoryMapper, Goods2GoodsCategory> {

    @Autowired
    Goods2GoodsCategoryMapper mapper;

    // 根据商品id删除全部商品2分类
    public int removeAllByGoodsId(Long id) {

        return mapper.delete(new LambdaQueryWrapper<Goods2GoodsCategory>().eq(Goods2GoodsCategory::getGoodsId, id));
    }
}
