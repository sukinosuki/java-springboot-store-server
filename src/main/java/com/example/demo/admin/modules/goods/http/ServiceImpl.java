package com.example.demo.admin.modules.goods.http;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.demo.admin.modules.goods.GoodsDao;
import com.example.demo.admin.modules.goods.GoodsMapper;
import com.example.demo.admin.modules.goods.model.GoodsForm;
import com.example.demo.admin.modules.goods_2_goods_category.Goods2GoodsCategoryDao;
import com.example.demo.common.AppException;
import com.example.demo.common.ListData;
import com.example.demo.model.Goods;
import com.example.demo.model.Goods2GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("goodsService")
public class ServiceImpl implements IService {
    @Autowired
    GoodsDao dao;

    @Autowired
    Goods2GoodsCategoryDao goods2GoodsCategoryDao;

    @Override
    @Transactional
    public void add(GoodsForm.Add form) {
        // 新增商品

        Goods goods = new Goods();
        goods.name = form.name;
        goods.description = form.description == null ? "" : form.description;
        goods.detail = form.detail == null ? "" : form.detail;
        goods.covers = String.join(",", form.covers);
        goods.onShelf = form.onShelf;
        goods.sort = form.sort == null ? 0 : form.sort;
        goods.price = form.price;
        goods.stock = form.stock;
        goods.fakeBaseSales = form.fakeBaseSales == null ? 0 : form.fakeBaseSales;
        goods.realSales = 0;

        boolean ok = dao.save(goods);
        if (!ok) {
            throw AppException.serveError("新增商品失败");
        }

        // 新增商品2分类
        List<Goods2GoodsCategory> goods2GoodsCategories = form.categoryIds.stream().map(id -> new Goods2GoodsCategory(goods.id, id)).toList();

        ok = goods2GoodsCategoryDao.saveBatch(goods2GoodsCategories);
        if (!ok) {
            throw AppException.serveError("新增商品2分类失败");
        }
    }

    @Override
    @Transactional
    public void update(GoodsForm.Add form) {
        // 更新商品
        LambdaUpdateWrapper<Goods> uw = new LambdaUpdateWrapper<>();

        uw.eq(Goods::getId, form.id)
                .set(Goods::getUpdatedAt, LocalDateTime.now())
                .set(Goods::getStock, form.stock)
                .set(Goods::getCovers, String.join(",", form.covers))
                .set(Goods::getName, form.name)
                .set(Goods::getPrice, form.price)
                .set(Goods::getFakeBaseSales, form.fakeBaseSales == null ? 0 : form.fakeBaseSales)
                .set(Goods::getSort, form.sort == null ? 0 : form.sort)
                .set(Goods::getDescription, form.description == null ? "" : form.description)
                .set(Goods::getDetail, form.detail == null ? "" : form.detail);

        boolean ok = dao.update(uw);
        if (!ok) {
            throw AppException.serveError("更新商品失败");
        }

        // 删除商品2分类
        goods2GoodsCategoryDao.removeAllByGoodsId(form.id);

        List<Goods2GoodsCategory> goods2GoodsCategories = form.categoryIds.stream().map(id -> new Goods2GoodsCategory(form.id, id)).toList();

        // 新增商品2分类
        ok = goods2GoodsCategoryDao.saveBatch(goods2GoodsCategories);

        if (!ok) {
            throw AppException.serveError("更新商品2分类失败");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // 删除商品(软删除?)
        boolean ok = dao.removeById(id);

        if (!ok) {
            throw AppException.actionFailError("删除商品失败");
        }

        // 删除商品2分类
        goods2GoodsCategoryDao.removeAllByGoodsId(id);
    }

    @Override
    public ListData<Goods> all(GoodsForm.Query form) {

        // TODO
        List<Goods> goods = dao.list();

        return ListData.of(goods, 0L);
    }
}
