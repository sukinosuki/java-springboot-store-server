package com.example.demo.admin.modules.sys_permission.http;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.demo.admin.modules.sys_permission.SysPermissionDao;
import com.example.demo.admin.modules.sys_permission.model.SysPermissionForm;
import com.example.demo.admin.modules.sys_role_2_sys_permission.SysRole2SysPermissionDao;
import com.example.demo.common.AppException;
import com.example.demo.common.enums.SysPermissionType;
import com.example.demo.model.SysPermission;
import com.example.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("sysPermissionService")
class ServiceImpl implements IService {

    @Autowired
    SysPermissionDao dao;

    @Autowired
    SysRole2SysPermissionDao sysRole2SysPermissionDao;

    public void add(SysPermissionForm.Add form) {

        // 添加permission
        // 区分type MENU | NODE
        SysPermission permission = new SysPermission();
        permission.enabled = form.enabled == null ? true : form.enabled;
        permission.type = form.type;
        permission.name = form.name;
        permission.description = form.description == null ? "" : form.description;
        permission.pid = form.pid == null ? 0 : form.pid;

        if (form.type == SysPermissionType.NODE) {
            permission.method = form.method;
            permission.url = form.url;
        }

        dao.save(permission);
    }

    @Override
    public void update(SysPermissionForm.Add form) {
        LambdaUpdateWrapper<SysPermission> uw = new LambdaUpdateWrapper<>();

        uw.eq(SysPermission::getId, form.id)
                .set(SysPermission::getUpdatedAt, LocalDateTime.now())
                .set(SysPermission::getType, form.type)
                .set(SysPermission::getName, form.name)
                .set(SysPermission::getDescription, form.description == null ? "" : form.description)
                .set(SysPermission::getEnabled, form.enabled == null ? true : form.enabled)
                .set(SysPermission::getMethod, form.method)
                .set(SysPermission::getUrl, form.url)
                .set(SysPermission::getPid, form.pid == null ? 0 : form.pid);

        boolean ok = dao.update(uw);

        if (!ok) {
            throw AppException.actionFailError(null);
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {

        // TODO: 删除前需要判断是否有角色关联了要删除的权限
        boolean ok = dao.removeBatchByIds(ids);

        if (!ok) {
            throw AppException.serveError("删除权限失败");
        }

        sysRole2SysPermissionDao.removeByPermissionIds(ids);
    }

    @Override
    public List<SysPermission> all() {

        List<SysPermission> list = dao.list();

        List<SysPermission> trees = Util.listToTree(list);

        return trees;
    }
}
