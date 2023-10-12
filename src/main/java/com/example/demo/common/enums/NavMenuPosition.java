package com.example.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NavMenuPosition implements ILabelValue<String> {
    APP_HOME_MAIN_MENU("app_home_main_menu", "app:首页:主要菜单"),
    PC_HOME_BANNER("pc_home_banner", "pc:首页:banner");

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
