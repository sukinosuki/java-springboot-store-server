package com.example.demo.admin.moduels.sys_permission.http;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.admin.moduels.sys_permission.SysPermissionDao;
import com.example.demo.admin.moduels.sys_permission.model.SysPermissionForm;
import com.example.demo.common.AppException;
import com.example.demo.model.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceImpl implements IService {

    @Autowired
    SysPermissionDao sysPermissionDao;

    public void add(SysPermissionForm.Add form) {

        // 添加permission
        // 区分type MENU | NODE
        SysPermission permission = new SysPermission();
        permission.enabled = form.enabled == null ? true : form.enabled;
        permission.method = form.method;
        permission.url = form.url;
        permission.type = form.type;
        permission.name = form.name;
        permission.description = form.description == null ? "" : form.description;
        permission.pid = form.pid == null ? 0 : form.pid;

        sysPermissionDao.save(permission);
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

        boolean ok = sysPermissionDao.update(uw);

        if (!ok) {
            throw AppException.actionFailError(null);
        }
    }

    @Override
    public void delete(List<Long> ids) {

        // TODO: 删除前需要判断是否有角色关联了要删除的权限

        boolean ok = sysPermissionDao.removeBatchByIds(ids);
        if (!ok) {
            throw AppException.actionFailError(null);
        }
    }

    @Override
    public void all() {

        // TODO: 获取树形结构的权限列表
    }
}
