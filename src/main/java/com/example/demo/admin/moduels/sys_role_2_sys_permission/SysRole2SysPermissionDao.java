package com.example.demo.admin.moduels.sys_role_2_sys_permission;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.SysRole2SysPermission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRole2SysPermissionDao extends ServiceImpl<SysRole2SysPermissionMapper, SysRole2SysPermission> {

    // 根据角色id删除关联
    public boolean removeByRoleId(Long roleId) {
        return this.remove(new LambdaQueryWrapper<SysRole2SysPermission>().eq(SysRole2SysPermission::getRoleId, roleId));
    }

    public boolean removeByPermissionIds(List<Long> permissionIds){
        return this.remove(new LambdaQueryWrapper<SysRole2SysPermission>().in(SysRole2SysPermission::getPermissionId, permissionIds));
    }
}
