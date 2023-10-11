package com.example.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum NavMenuActionType {
    NO_ACTION(1, "no action"),
    OPEN_PAGE(2, "open page"),

    OPEN_URL(3, "open url");

    public Integer value;
    public String label;
}
