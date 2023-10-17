package com.example.demo.admin.modules.goods_category.http;

import com.example.demo.admin.modules.goods_category.model.GoodsCategoryForm;
import com.example.demo.common.R;
import com.example.demo.model.GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController("goodsCategoryController")
@RequestMapping("/admin/api/v1/goods-category")
public class Controller {

    @Autowired
    IService service;

    @PostMapping
    R<Void> add(@Valid @RequestBody @NotNull GoodsCategoryForm.Add form) {

        service.add(form);

        return R.ok();
    }

    @PutMapping("/{id}")
    R<Void> update(@Valid @RequestBody @NotNull GoodsCategoryForm.Add form, @PathVariable Long id) {
        form.id = id;

        service.update(form);

        return R.ok();
    }

    @GetMapping
    R<List<GoodsCategory>> all() {
        List<GoodsCategory> listData = service.all();

        return R.ok(listData);
    }

    @DeleteMapping("/{id}")
    R<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return R.ok();
    }
}
