package com.example.demo.admin.modules.sys_role.http;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.admin.modules.sys_role.SysRoleDao;
import com.example.demo.admin.modules.sys_role.SysRoleMapper;
import com.example.demo.admin.modules.sys_role.model.SysRoleForm;
import com.example.demo.admin.modules.sys_role_2_sys_permission.SysRole2SysPermissionDao;
import com.example.demo.admin.modules.sys_user_2_sys_role.SysUser2SysRoleDao;
import com.example.demo.common.AppException;
import com.example.demo.common.ListData;
import com.example.demo.common.enums.QueryOrder;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysRole2SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("sysRoleService")
class ServiceImpl implements IService {
    @Autowired
    SysRoleDao dao;

    @Autowired
    SysRoleMapper mapper;

    @Autowired
    SysRole2SysPermissionDao sysRole2SysPermissionDao;

    @Autowired
    SysUser2SysRoleDao sysUser2SysRoleDao;

    @Override
    @Transactional
    public void add(SysRoleForm.Add form) {
        // 添加角色

        SysRole sysRole = new SysRole();
        sysRole.description = form.description == null ? "" : form.description;
        sysRole.enabled = form.enabled == null ? true : form.enabled;
        sysRole.name = form.name;

        boolean ok = dao.save(sysRole);
        if (!ok) {
            throw AppException.serveError("新增角色失败");
        }

        // 添加角色2权限

        List<SysRole2SysPermission> role2SysPermissions = form.roleIds.stream().map(id -> new SysRole2SysPermission(sysRole.id, id)).toList();
        // 存空列表时会返回false
        ok = sysRole2SysPermissionDao.saveBatch(role2SysPermissions);
        if (!ok) {
            throw AppException.serveError("新增角色2权限失败");
        }
    }

    @Override
    @Transactional
    public void update(SysRoleForm.Add form) {
        // 更新角色
        LambdaUpdateWrapper<SysRole> uw = new LambdaUpdateWrapper<>();
        uw.eq(SysRole::getId, form.id)
                .set(SysRole::getUpdatedAt, LocalDateTime.now())
                .set(SysRole::getName, form.name)
                .set(SysRole::getDescription, form.description == null ? "" : form.description)
                .set(SysRole::getEnabled, form.enabled == null ? true : form.enabled);


        // 更新一个不存在的id会返回false
        boolean ok = dao.update(uw);
        if (!ok) {
            throw AppException.serveError("更新角色失败");
        }

        // 删除原来的role2permission
        ok = sysRole2SysPermissionDao.remove(new QueryWrapper<SysRole2SysPermission>().eq("role_id", form.id));
        if (!ok) {
            throw AppException.serveError("删除角色2权限失败");
        }

        // 添加新的role2permission
        List<SysRole2SysPermission> sysRole2SysPermissions = form.roleIds.stream().map(id -> new SysRole2SysPermission(form.id, id)).toList();

        ok = sysRole2SysPermissionDao.saveBatch(sysRole2SysPermissions);
        if (!ok) {
            throw AppException.serveError("新增角色2权限失败");
        }

        // TODO: 删除redis缓存用户对应的角色的权限列表
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // 检查是否有用色拥有该角色，如果存在用户拥有该角色，则不能删除

        Boolean exist = sysUser2SysRoleDao.existByRoleId(id);
        if (exist) {
            throw AppException.actionFailError("存在用户绑定了该角色, 不能删除");
        }

        // 要删除的角色没有用户绑定, 删除角色
        boolean ok = dao.removeById(id);
        if (!ok) {
            throw AppException.serveError("删除角色失败");
        }

        // 删除user2role
//        ok = sysUser2SysRoleDao.removeAllByRoleId(id);
//        if (!ok) {
//            throw AppException.serveError("删除用户");
//        }
        // 删除role2permission
        ok = sysRole2SysPermissionDao.removeByRoleId(id);
        if (!ok) {
            throw AppException.serveError("删除role2permission失败");
        }
    }

    @Override
    public ListData<SysRole> all(SysRoleForm.Query form) {


        QueryWrapper<SysRole> qw = new QueryWrapper<SysRole>().orderBy(true, form.order == QueryOrder.ASC, form.orderField);

//        QueryWrapper<SysRole> qw = new QueryWrapper<SysRole>().orderBy(true, form.order== QueryOrder.ASC, form.orderField);
        Page<SysRole> page = new Page<>((form.page - 1), form.size);

        Page<SysRole> sysRolePage = dao.page(page, qw);
        sysRolePage.getPages();

        return ListData.of(sysRolePage.getRecords(), sysRolePage.getSize());
    }
}
