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

    // TIP: @JsonValue作用是对响应的枚举转换成json时使用该字段(这里是value). 作用2是对body参数的枚举做转换
    @JsonValue
    // TIP: @EnumValue作用是对数据库表实体类的枚举类型在保存到数据库时, 值使用注解的字段(这里是value)
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
