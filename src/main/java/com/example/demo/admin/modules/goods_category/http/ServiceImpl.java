package com.example.demo.admin.modules.goods_category.http;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.demo.admin.modules.goods_category.GoodsCategoryDao;
import com.example.demo.admin.modules.goods_category.model.GoodsCategoryForm;
import com.example.demo.common.AppException;
import com.example.demo.common.ListData;
import com.example.demo.model.GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("goodsCategoryService")
public class ServiceImpl implements IService {
    @Autowired
    GoodsCategoryDao dao;

    @Override
    @Transactional
    public void add(GoodsCategoryForm.Add form) {
        GoodsCategory category = new GoodsCategory();
        category.name = form.name;
        category.pid = form.pid == null ? 0 : form.pid;
        category.sort = form.sort == null ? 0 : form.sort;
        category.enabled = form.enabled == null ? true : form.enabled;

        boolean ok = dao.save(category);
        if (!ok) {
            throw AppException.serveError("新增商品分类失败");
        }
    }

    // 根据pid获取level
//    Integer mustGetLevelFromPid(Long pid) {
//        int level = 0;
//        // 根据pid设置level
//        if (pid == null || pid == 0) {
//            return level;
//        }
//
//        GoodsCategory category = dao.getById(pid);
//        if (category == null) {
//            throw AppException.actionFailError("操作失败: 无效的pid");
//        }
//
//        return category.level + 1;
//    }

    @Override
    public void update(GoodsCategoryForm.Add form) {
        LambdaUpdateWrapper<GoodsCategory> uw = new LambdaUpdateWrapper<>();
        uw.eq(GoodsCategory::getId, form.id)
                .set(GoodsCategory::getName, form.name)
                .set(GoodsCategory::getUpdatedAt, LocalDateTime.now())
                .set(GoodsCategory::getEnabled, form.enabled == null ? true : form.enabled)
                .set(GoodsCategory::getSort, form.sort == null ? 0 : form.sort);

        boolean ok = dao.update(uw);
        if (!ok) {
            throw AppException.serveError("更新商品分类失败");
        }
    }

    @Override
    public void delete(Long id) {
        // 查看分类是否有商品绑定了该分类

        // 根据id删除商品分类
        boolean ok = dao.removeById(id);

        if (!ok) {
            throw AppException.actionFailError("删除商品分类失败");
        }

        // 删除该id下的所有子级分类
    }

    @Override
    public ListData<GoodsCategory> all() {
        List<GoodsCategory> categories = dao.list();

        return ListData.of(categories, 0L);
    }
}
