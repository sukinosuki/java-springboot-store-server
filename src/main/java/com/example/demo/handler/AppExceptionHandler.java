package com.example.demo.handler;

import com.example.demo.common.AppException;
import com.example.demo.common.R;
import com.example.demo.util.NullUtil;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {


    // TODO: 日志记录请求相关信息
    @ExceptionHandler(Exception.class)
    R handleException(Exception e) {
        log.error("全局异常, type:".concat(e.getClass().getName()).concat(", msg: ").concat(e.getMessage()));

        return R.serverError(e.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    R SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {

        log.error("sql错误, type: ".concat(e.getClass().getName()).concat(", msg: ").concat(e.getMessage()));

        return R.serverError(e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    R DuplicateKeyException(DuplicateKeyException e) {

        log.error("sql: 资源重复错误, type: ".concat(e.getClass().getName()).concat(", msg: ").concat(e.getMessage()));

        return R.actionFail("资源重复", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    R handleValidateException(MethodArgumentNotValidException e) {
        String msg = "";

        if (e.hasErrors()) {
            msg = e.getAllErrors().get(0).getDefaultMessage();
        }

        return R.paramError(msg, e.getMessage());
    }

    // type:org.springframework.http.converter.HttpMessageNotReadableException, msg: Required request body is missing: com.example.demo.common.R com.example.demo.admin.moduels.nav_menu_position.NavMenuPositionController.add(com.example.demo.admin.moduels.nav_menu_position.model.NavMenuPositionForm$Add)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    R handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        String msg = "参数错误";
        if (e.getCause() instanceof MismatchedInputException) {
            msg = "参数解析错误";
        }

        if (NullUtil.null2String(e.getMessage()).contains("Required request body is missing:")) {
            msg = "参数体不能为空";
        }

        log.error("参数解析错误: type: ".concat(e.getClass().getName()).concat(", msg: ".concat(NullUtil.null2String(e.getMessage()))));

        return R.paramError(msg, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    R handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("请求方法不支持, msg: ".concat(e.getMessage()));

        return R.recordNotFound();
    }

    @ExceptionHandler(AppException.class)
    R handleAppException(AppException e) {
        log.warn("捕获自定义异常: msg: ".concat(NullUtil.null2String(e.message)));

        return R.appException(e);
    }
}
