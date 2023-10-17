package com.example.demo.admin.modules.sys_user.http;

import com.example.demo.admin.modules.sys_user.model.SysUserForm;
import com.example.demo.admin.modules.sys_user.model.SysUserResSimple;
import com.example.demo.common.ListData;
import com.example.demo.common.R;
import com.example.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController("sysUserController")
@RequestMapping("/admin/api/v1/sys-user")
public class Controller {

    @Autowired
    IService service;

    @PostMapping
    R<Void> add(@Valid @RequestBody @NotNull SysUserForm.Add form) {

        ValidationUtil.validateByGroup(form, SysUserForm.Add.AddGroup.class);

        service.add(form);
        return R.ok();
    }

    @PutMapping("/{id}")
    R<Void> update(@Valid @RequestBody @NotNull SysUserForm.Add form, @PathVariable Long id) {

        form.id = id;

        service.update(form);

        return R.ok();
    }

    @DeleteMapping("/{id}")
    R<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return R.ok();
    }

    @GetMapping
    R<ListData<SysUserResSimple>> list(@RequestParam(value = "page", required = false) Long page,
                              @RequestParam(value = "size", required = false) Long size) {

        SysUserForm.Query query = new SysUserForm.Query();
        query.page = page;
        query.size = size;

        ListData<SysUserResSimple> listData = service.all(query);

        return R.ok(listData);
    }

    @GetMapping("/{id}")
    R<SysUserResSimple> detail(@PathVariable Long id) {
        SysUserResSimple sysUser = service.detail(id);

        return R.ok(sysUser);
    }
}
