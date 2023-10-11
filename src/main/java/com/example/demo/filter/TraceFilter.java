package com.example.demo.filter;

import com.example.demo.component.thread_local.TraceIdContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Order(10)
@Component
@Slf4j
public class TraceFilter extends AbstractRequestLoggingFilter {

    @Autowired
    TraceIdContext traceIdContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String traceId = UUID.randomUUID().toString().replace("-", "");

        response.setHeader("trace-id", traceId);

        traceIdContext.setTraceId(traceId);

//        filterChain.doFilter(request, response);


        super.doFilterInternal(request, response, filterChain);

    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

        log.info("请求 before");
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

        log.info("请求 after");
        traceIdContext.clear();

    }
}
