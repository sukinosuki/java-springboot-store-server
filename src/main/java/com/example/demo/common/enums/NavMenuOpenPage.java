package com.example.demo.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NavMenuOpenPage {

    NEWS_DETAIL("news_detail", "news detail"),
    NEWS_LIST("news_list", "news list");

    public final String value;
    public final String label;
}
