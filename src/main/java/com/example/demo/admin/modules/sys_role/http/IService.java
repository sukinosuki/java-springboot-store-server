package com.example.demo.admin.modules.sys_role.http;

import com.example.demo.admin.modules.sys_role.model.SysRoleForm;
import com.example.demo.common.ListData;
import com.example.demo.model.SysRole;

interface IService {

    void add(SysRoleForm.Add form);

    void update(SysRoleForm.Add form);

    void delete(Long id);

    ListData<SysRole> all(SysRoleForm.Query form);
}
