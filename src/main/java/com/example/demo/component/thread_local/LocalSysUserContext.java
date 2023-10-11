package com.example.demo.component.thread_local;

import com.example.demo.common.LocalSysUser;
import com.example.demo.model.SysUser;
import org.springframework.stereotype.Component;

// 使用thread local保存当前请求的用户授权信息
@Component
public class LocalSysUserContext {

    ThreadLocal<LocalSysUser> local = new ThreadLocal<>();

    public void set(Long id, SysUser user, Exception exception) {
        local.set(new LocalSysUser(id, user, exception));
    }

    public LocalSysUser get() {
        return local.get();
    }

    public void clear() {
        local.remove();
    }
}
