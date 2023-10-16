package com.example.demo.admin.moduels.sys_user_2_sys_role;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.SysUser2SysRole;
import org.springframework.stereotype.Service;

@Service
public class SysUser2SysRoleDao extends ServiceImpl<SysUser2SysRoleMapper, SysUser2SysRole> {

    // 根据role_id判断是否存在
    public Boolean existByRoleId(Long roleId) {
        long count = this.count(new LambdaQueryWrapper<SysUser2SysRole>().eq(SysUser2SysRole::getRoleId, roleId));

        return count > 0;
    }

    // 根据role_id删除全部
    public Boolean removeAllByRoleId(Long roleId) {
        LambdaQueryWrapper<SysUser2SysRole> qw = new LambdaQueryWrapper<SysUser2SysRole>().eq(SysUser2SysRole::getRoleId, roleId);

        return this.remove(qw);
    }
}
