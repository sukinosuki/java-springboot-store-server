package com.example.demo.admin.modules.sys_role.http;

import com.example.demo.admin.modules.sys_role.model.SysRoleForm;
import com.example.demo.common.ListData;
import com.example.demo.common.R;
import com.example.demo.common.enums.QueryOrder;
import com.example.demo.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@RestController("sysRoleController")
@RequestMapping("/admin/api/v1/sys-role")
public class Controller {

    @Autowired
    IService service;

    @PostMapping
    R add(@Valid @RequestBody @NotNull SysRoleForm.Add form) {

        service.add(form);

        return R.ok();
    }

    @PutMapping("/{id}")
    R update(@Valid @RequestBody @NotNull SysRoleForm.Add form, @PathVariable Long id) {
        form.id = id;

        service.update(form);

        return R.ok();
    }

    @DeleteMapping("/{id}")
    R delete(@PathVariable Long id) {

        service.delete(id);

        return R.ok();
    }

    // TODO: 校验@RequestParam
    @GetMapping
    R<ListData<SysRole>> all(@RequestParam(value = "page", required = false) Long page,
                             @RequestParam(value = "size", required = false) Long size,
                             @RequestParam(value = "order", required = false) QueryOrder order,
                             @Valid @Pattern(regexp = "^(id|sort)$", message = "orderField仅支持[id|sort]") @RequestParam(value = "orderField", required = false) String orderField) {

        SysRoleForm.Query query = new SysRoleForm.Query();
        query.page = page == null ? 1 : page;
        query.size = size == null ? 10 : size;
        query.order = order == null ? QueryOrder.DESC : order;
        query.orderField = orderField == null ? "id" : orderField;

        ListData<SysRole> roles = service.all(query);

        return R.ok(roles);
    }
}
