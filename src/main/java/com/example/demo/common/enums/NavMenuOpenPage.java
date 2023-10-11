package com.example.demo.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NavMenuOpenPage implements ILabelValue<String> {

    NEWS_DETAIL("news_detail", "news detail"),
    NEWS_LIST("news_list", "news list");

    @JsonValue
    public final String value;

    public final String label;

    @Override
    public String getValue() {
        return this.getValue();
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
