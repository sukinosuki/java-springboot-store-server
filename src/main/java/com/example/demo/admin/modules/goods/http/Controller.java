package com.example.demo.admin.modules.goods.http;

import com.example.demo.admin.modules.goods.model.GoodsForm;
import com.example.demo.common.ListData;
import com.example.demo.common.R;
import com.example.demo.common.enums.QueryOrder;
import com.example.demo.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController("goodsController")
@RequestMapping("/admin/api/v1/goods")
public class Controller {

    @Autowired
    IService service;

    @GetMapping
    R<ListData<Goods>> all(@RequestParam(value = "page", required = false) Long page,
                           @RequestParam(value = "size", required = false) Long size,
                           @RequestParam(value = "order", required = false) QueryOrder order,
                           @RequestParam(value = "orderField", required = false) String orderField) {
        GoodsForm.Query query = new GoodsForm.Query();
        query.page = page;
        query.size = size;
        query.order = order;
        query.orderField = orderField;
        ListData<Goods> listData = service.all(query);

        return R.ok(listData);
    }

    @PostMapping
    R<Void> add(@Valid @RequestBody @NotNull GoodsForm.Add form) {
        service.add(form);

        return R.ok();
    }

    @PutMapping("/{id}")
    R<Void> update(@Valid @RequestBody @NotNull GoodsForm.Add form, @PathVariable Long id) {
        form.id = id;

        service.update(form);

        return R.ok();
    }

    @DeleteMapping("/{id}")
    R<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return R.ok();
    }
}
