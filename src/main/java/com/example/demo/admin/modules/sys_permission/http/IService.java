package com.example.demo.admin.modules.sys_permission.http;

import com.example.demo.admin.modules.sys_permission.model.SysPermissionForm;
import com.example.demo.model.SysPermission;

import java.util.List;

interface IService {

    void add(SysPermissionForm.Add form);

    void update(SysPermissionForm.Add form);

    void delete(List<Long> ids);

    List<SysPermission> all();
}
