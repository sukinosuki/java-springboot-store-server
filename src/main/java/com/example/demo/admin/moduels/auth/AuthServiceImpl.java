package com.example.demo.admin.moduels.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.model.SysUser;
import com.example.demo.admin.moduels.auth.model.LoginRes;
import com.example.demo.admin.moduels.auth.model.LoginResUser;
import com.example.demo.admin.moduels.auth.model.RegisterForm;
import com.example.demo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    // 管理端注册
    // 注册了超级管理员账号之后就不需要了
    @Override
    public LoginRes register(RegisterForm form) {
        SysUser sysUser = new SysUser();
        sysUser.setAvatar("");
        sysUser.setUsername(form.getUsername());
        sysUser.setPassword(passwordEncoder.encode(form.getPassword()));
        sysUser.setEnabled(true);
        sysUser.setNickname(form.getUsername());

        sysUserMapper.insert(sysUser);

        LoginResUser loginResUser = new LoginResUser(
                sysUser.getId(),
                sysUser.getUsername(),
                sysUser.getNickname(),
                sysUser.getAvatar(),
                sysUser.getEnabled()
        );

        String token = jwtUtil.generateToken(sysUser.getId());

        LoginRes loginRes = new LoginRes(loginResUser, token);

        return loginRes;
    }

    // 管理端登录
    @Override
    public LoginRes login(RegisterForm form) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(SysUser::getUsername, form.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);

        if (sysUser == null) {
            throw new RuntimeException("用户名或密码不正确");
        }

        boolean isMatched = passwordEncoder.matches(form.getPassword(), sysUser.getPassword());

        if (!isMatched) {
            // TODO: 招聘自定义错误
            throw new RuntimeException("用户名或密码不正确");
        }

        LoginResUser loginResUser = new LoginResUser(
                sysUser.getId(),
                sysUser.getUsername(),
                sysUser.getNickname(),
                sysUser.getAvatar(),
                sysUser.getEnabled()
        );

        String token = jwtUtil.generateToken(sysUser.getId());

        LoginRes loginRes = new LoginRes(loginResUser, token);

        return loginRes;
    }

    @Override
    public LoginRes auth(Long uid) {
        return null;
    }
}
