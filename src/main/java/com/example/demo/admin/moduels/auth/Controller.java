package com.example.demo.admin.moduels.auth;

import com.example.demo.admin.moduels.auth.model.LoginRes;
import com.example.demo.admin.moduels.auth.model.RegisterForm;
import com.example.demo.common.LocalSysUser;
import com.example.demo.component.thread_local.LocalSysUserContext;
import com.example.demo.component.thread_local.TraceIdContext;
import com.example.demo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("/admin/api/v1/auth")
@RestController(value = "AuthController")
@Slf4j
public class Controller {

    @Autowired
    AuthService authService;

    @Autowired
    TraceIdContext traceIdContext;

    @Autowired
    LocalSysUserContext localSysUserContext;

    @PostMapping("/register")
    R<LoginRes> register(@Valid @RequestBody @NotNull RegisterForm registerForm) {
        log.info("注册开始, traceId: ".concat(traceIdContext.getTraceId()));

        LoginRes loginRes = authService.register(registerForm);

        return R.ok(loginRes);
    }

    @PostMapping("/login")
    R<LoginRes> login(@Valid @RequestBody @NotNull RegisterForm loginForm) throws InterruptedException {
        log.info("登录开始, traceId: ".concat(traceIdContext.getTraceId()));

        LoginRes res = authService.login(loginForm);

        return R.ok(res);
    }

    @GetMapping("/info")
    R info() {
        LocalSysUser localSysUser = localSysUserContext.get();

        log.info("获取用户信息, user: ".concat(localSysUser.toString()));

        return R.ok(localSysUser);
    }
}
