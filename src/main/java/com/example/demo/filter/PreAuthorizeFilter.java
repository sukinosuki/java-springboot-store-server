package com.example.demo.filter;

import com.example.demo.common.AppException;
import com.example.demo.component.thread_local.LocalSysUserContext;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.Pair;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class PreAuthorizeFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    LocalSysUserContext localSysUserContext;

    String headerTokenKey = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(headerTokenKey);
        if (token == null || Objects.equals(token, "")) {
            localSysUserContext.set(null, null, AppException.unAuthorizedError(null));

            filterChain.doFilter(request, response);

            return;
        }

        Pair<Claims, Exception> pair = jwtUtil.parseToken(token);
        if (pair.second != null) {
            log.error("解析token失败, err: ".concat(pair.second.getMessage()));

            localSysUserContext.set(null, null, pair.second);
        } else {
            log.info("解析token成功, claims: ".concat(pair.first.toString()));
            // TODO
            localSysUserContext.set(Long.parseLong(pair.first.getSubject()), null, null);
        }

        filterChain.doFilter(request, response);

        log.info("预认证filter结束");

    }
}
