package com.example.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum NavMenuActionType implements ILabelValue<Integer>, IEnum<Integer> {
    NO_ACTION(1, "no action"),
    OPEN_PAGE(2, "open page"),
    OPEN_URL(3, "open url");

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
