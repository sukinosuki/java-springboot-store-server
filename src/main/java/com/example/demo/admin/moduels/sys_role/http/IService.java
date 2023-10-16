package com.example.demo.admin.moduels.sys_role.http;

import com.example.demo.admin.moduels.sys_role.model.SysRoleForm;
import com.example.demo.common.ListData;
import com.example.demo.model.SysRole;

import java.util.ArrayList;

interface IService {

    void add(SysRoleForm.Add form);

    void update(SysRoleForm.Add form);

    void delete(Long id);

    ListData<SysRole> all(SysRoleForm.Query form);
}
