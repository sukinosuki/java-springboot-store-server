package com.example.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SysPermissionMethod implements ILabelValue<String>, IEnum<String> {
    GET("GET", "get"),
    POST("POST", "post"),
    DELETE("DELETE", "delete"),
    PUT("PUT", "put");

    @EnumValue
    @JsonValue
    public final String value;
    public final String label;

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getKey() {
        return this.value;
    }
}
