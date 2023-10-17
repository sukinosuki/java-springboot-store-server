package com.example.demo.admin.modules.sys_permission.http;

import com.example.demo.admin.modules.sys_permission.model.SysPermissionForm;
import com.example.demo.common.R;
import com.example.demo.common.enums.SysPermissionType;
import com.example.demo.model.SysPermission;
import com.example.demo.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController("SysPermissionController")
@RequestMapping("/admin/api/v1/sys-permission")
public class Controller {

    @Autowired
    IService service;

    @GetMapping
    R<List<SysPermission>> list() {
        List<SysPermission> list = service.all();

        return R.ok(list);
    }

    @PostMapping
    R<Void> add(@Valid @RequestBody @NotNull SysPermissionForm.Add form) {

        if (form.type == SysPermissionType.NODE) {
            ValidationUtil.validateByGroup(form, SysPermissionForm.Add.NodeGroup.class);
        }

        service.add(form);

        return R.ok();
    }


    @PutMapping("/{id}")
    R<Void> update(@Valid @RequestBody @NotNull SysPermissionForm.Add form, @PathVariable Long id) {
        if (form.type == SysPermissionType.NODE) {
            ValidationUtil.validateByGroup(form, SysPermissionForm.Add.NodeGroup.class);
        }

        form.id = id;

        service.update(form);

        return R.ok();
    }

    @DeleteMapping("/{id}")
    R<Void> delete(@PathVariable Long id) {

        service.delete(List.of(id));

        return R.ok();
    }
}

