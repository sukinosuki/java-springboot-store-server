package com.example.demo.admin.modules.goods.http;

import com.example.demo.admin.modules.goods.model.GoodsForm;
import com.example.demo.common.ListData;
import com.example.demo.model.Goods;

interface IService {

    void add(GoodsForm.Add form);

    void update(GoodsForm.Add form);

    void delete(Long id);

    ListData<Goods> all(GoodsForm.Query form);
}
