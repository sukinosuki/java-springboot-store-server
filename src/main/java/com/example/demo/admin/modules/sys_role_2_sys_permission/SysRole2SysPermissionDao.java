package com.example.demo.admin.modules.sys_role_2_sys_permission;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.SysRole2SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRole2SysPermissionDao extends ServiceImpl<SysRole2SysPermissionMapper, SysRole2SysPermission> {

    @Autowired
    SysRole2SysPermissionMapper mapper;

    // 根据角色id删除关联
    public boolean removeByRoleId(Long roleId) {
        return this.remove(new LambdaQueryWrapper<SysRole2SysPermission>().eq(SysRole2SysPermission::getRoleId, roleId));
    }

    public int removeByPermissionIds(List<Long> permissionIds){
//        return this.remove(new LambdaQueryWrapper<SysRole2SysPermission>().in(SysRole2SysPermission::getPermissionId, permissionIds));
        LambdaQueryWrapper<SysRole2SysPermission> qw = new LambdaQueryWrapper<SysRole2SysPermission>().in(SysRole2SysPermission::getPermissionId, permissionIds);

        return mapper.delete(qw);
    }
}
