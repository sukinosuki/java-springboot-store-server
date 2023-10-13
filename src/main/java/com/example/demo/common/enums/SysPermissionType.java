package com.example.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum SysPermissionType  implements ILabelValue<Integer> , IEnum<Integer> {
    MENU(1, "menu"),
    NODE(2, "node");

    @JsonValue
    @EnumValue
    public Integer value;
    public String label;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getKey() {
        return this.value.toString();
    }
}
