package com.example.demo.admin.modules.sys_user.http;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.demo.admin.modules.sys_user.SysUserDao;
import com.example.demo.admin.modules.sys_user.SysUserMapper;
import com.example.demo.admin.modules.sys_user.model.SysUserForm;
import com.example.demo.admin.modules.sys_user.model.SysUserResSimple;
import com.example.demo.admin.modules.sys_user_2_sys_role.SysUser2SysRoleDao;
import com.example.demo.common.AppException;
import com.example.demo.common.ListData;
import com.example.demo.model.SysUser;
import com.example.demo.model.SysUser2SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("sysUserService")
public class ServiceImpl implements IService {
    @Autowired
    SysUserDao dao;

    @Autowired
    SysUserMapper mapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    SysUser2SysRoleDao sysUser2SysRoleDao;

    @Override
    @Transactional
    public void add(SysUserForm.Add form) {
        SysUser sysUser = new SysUser();
        sysUser.avatar = form.avatar == null ? "" : form.avatar;
        sysUser.username = form.username;
        sysUser.password = passwordEncoder.encode(form.password);
        sysUser.nickname = form.username;
        sysUser.enabled = form.enabled == null ? true : form.enabled;

        // 添加user
        boolean ok = dao.save(sysUser);
        if (!ok) {
            throw AppException.serveError("新增用户失败");
        }

        // 添加user2role
        List<SysUser2SysRole> sysUser2SysRoles = form.roleIds.stream().map(id -> new SysUser2SysRole(sysUser.id, id)).toList();

        ok = sysUser2SysRoleDao.saveBatch(sysUser2SysRoles);
        if (!ok) {
            throw AppException.serveError("新增用户2角色失败");
        }
    }

    @Override
    @Transactional
    public void update(SysUserForm.Add form) {

        // 更新user
        LambdaUpdateWrapper<SysUser> uw = new LambdaUpdateWrapper<SysUser>().eq(SysUser::getId, form.id)
                .set(SysUser::getUpdatedAt, LocalDateTime.now())
                .set(SysUser::getPassword, form.password)
                .set(SysUser::getAvatar, form.avatar == null ? "" : form.avatar)
                .set(SysUser::getEnabled, form.enabled == null ? true : form.enabled);

        boolean ok = dao.update(uw);
        if (!ok) {
            throw AppException.serveError("更新用户失败");
        }

        // 删除user2role
        sysUser2SysRoleDao.removeAllByUid(form.id);

        // 新增user2role
        List<SysUser2SysRole> sysUser2SysRoles = form.roleIds.stream().map(id -> new SysUser2SysRole(form.id, id)).toList();

        ok = sysUser2SysRoleDao.saveBatch(sysUser2SysRoles);
        if (!ok) {
            throw AppException.serveError("新增用户2角色失败");
        }

        // TODO: 处理角色权限缓存
    }

    @Override
    @Transactional
    public void delete(Long id) {

        // 删除角色
        boolean ok = dao.removeById(id);
        if (!ok) {
            throw AppException.serveError("删除用户失败");
        }

        // 删除角色2权限
        int effect = sysUser2SysRoleDao.removeAllByUid(id);

        // TODO: 处理用户、权限缓存
    }

    @Override
    public ListData<SysUserResSimple> all(SysUserForm.Query form) {
        List<SysUserResSimple> users = mapper.all(form);

        return ListData.of(users, 0L);
    }

    @Override
    public SysUserResSimple detail(Long id) {
        SysUserResSimple user = mapper.detail(id);

        if (user == null) {
            throw AppException.recordNotFoundError(null);
        }

        return user;
    }
}
