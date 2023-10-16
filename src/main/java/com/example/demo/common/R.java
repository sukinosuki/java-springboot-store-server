package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.PushBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    public Integer code;
    public String msg;
    public String errMsg;
    public T data;

    static public <T> R<T> ok() {
        R r = new R<T>();

        r.code = 0;
        r.msg = "ok";

        return r;
    }

//    public static <T> R<ListData<T>> okList() {
//
//    }

    public static <T> R<T> ok(T data) {

        R r = new R<T>();

        r.code = 0;
        r.msg = "ok";
        r.data = data;

        return r;
    }

    public static R appException(AppException e) {
        R r = new R();
        r.code = e.code.code;
        r.msg = e.message == null ? e.code.msg : e.message;

        return r;
    }

    public static R serverError(String errMsg) {
        R r = new R();
        r.code = 500; // TODO
        r.errMsg = errMsg;
        r.msg = "服务器错误"; // TODO

        return r;
    }

    public static R actionFail(String msg, String errMsg) {
        R r = new R();

        r.code = 400;
        r.msg = msg;
        r.errMsg = errMsg;

        return r;
    }

    public static R paramError(String msg, String errMsg) {
        R r = new R();

        r.code = AppErrorCode.PARAM_ERROR.code;
        r.msg = msg;
        r.errMsg = errMsg;

        return r;
    }

    public static R recordNotFound() {
        R r = new R();
        r.code = AppErrorCode.RECORD_NOT_FOUND.code;
        r.msg = AppErrorCode.RECORD_NOT_FOUND.msg;

        return r;
    }

}
