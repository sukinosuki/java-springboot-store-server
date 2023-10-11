package com.example.demo.component.thread_local;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

// 保存当前请求的trace id
@Component
public class TraceIdContext {

    String TRACE_ID_KEY = "trace_id";

    public void setTraceId(String id) {
        MDC.put(TRACE_ID_KEY, id);
    }

    public void clear() {
        MDC.clear();
    }

    public String getTraceId() {
        return MDC.get(TRACE_ID_KEY);
    }
}
