package com.example.demo.common;


import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public class AppException extends RuntimeException {

    public AppErrorCode code;
    @Nullable
    public String message;

    public static AppException unAuthorizedError(String msg) {

        return new AppException(AppErrorCode.UNAUTHORIZED, msg);
    }

    public static AppException actionFailError(String msg){
        return new AppException(AppErrorCode.ACTION_FAIL, msg);
    }

    public static AppException serveError(String msg) {
        return new AppException(AppErrorCode.SERVER_ERROR, msg);
    }

    public static AppException recordNotFoundError(String msg) {
        return new AppException(AppErrorCode.RECORD_NOT_FOUND, msg);
    }

    public static AppException paramError(String msg){
        return new AppException(AppErrorCode.PARAM_ERROR, msg);
    }
}
