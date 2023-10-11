package com.example.demo.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NavMenuPosition {
    APP_HOME_MAIN_MENU("app_home_main_menu", "app:首页:主要菜单"),
    PC_HOME_BANNER("pc_home_banner", "pc:首页:banner");

    public final String value;
    public final String label;
}
