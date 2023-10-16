package com.example.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum QueryOrder implements ILabelValue<String>, IEnum<String> {
    ASC("asc", "asc"),
    DESC("desc","desc");

    @JsonValue
    @EnumValue
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
