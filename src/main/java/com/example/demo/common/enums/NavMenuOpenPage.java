package com.example.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NavMenuOpenPage implements ILabelValue<String> {

    NEWS_DETAIL("news_detail", "news detail"),
    NEWS_LIST("news_list", "news list");

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
