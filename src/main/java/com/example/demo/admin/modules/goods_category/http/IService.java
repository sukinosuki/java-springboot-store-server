package com.example.demo.admin.modules.goods_category.http;


import com.example.demo.admin.modules.goods_category.model.GoodsCategoryForm;
import com.example.demo.model.GoodsCategory;

import java.util.List;

interface IService {

    void add(GoodsCategoryForm.Add form);

    void update(GoodsCategoryForm.Add form);

    void delete(Long id);

    List<GoodsCategory> all();
}
