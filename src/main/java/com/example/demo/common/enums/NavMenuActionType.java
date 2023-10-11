package com.example.demo.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum NavMenuActionType implements ILabelValue<Integer> {
    NO_ACTION(1, "no action"),
    OPEN_PAGE(2, "open page"),

    OPEN_URL(3, "open url");

    @JsonValue
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
}
