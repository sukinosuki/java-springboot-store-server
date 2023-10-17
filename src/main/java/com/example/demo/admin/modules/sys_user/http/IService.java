package com.example.demo.admin.modules.sys_user.http;


import com.example.demo.admin.modules.sys_user.model.SysUserForm;
import com.example.demo.admin.modules.sys_user.model.SysUserResSimple;
import com.example.demo.common.ListData;

interface IService {

    void add(SysUserForm.Add form);

    void update(SysUserForm.Add form);

    void delete(Long id);

    // all代表获取列表
    ListData<SysUserResSimple> all(SysUserForm.Query form);

    SysUserResSimple detail(Long id);
}
