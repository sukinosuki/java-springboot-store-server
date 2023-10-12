package com.example.demo.admin.moduels.nav_menu.model;

import com.example.demo.common.enums.NavMenuActionType;
import com.example.demo.common.enums.NavMenuOpenPage;
import com.example.demo.common.enums.NavMenuPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

public class NavMenuForm {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Add {
        @NotNull(message = "name不能为空")
        @NotEmpty(message = "name不能为空")
        @Size(max = 12, message = "name长度在12字符内")
        public String name;

        @Size(max = 255, message = "cover长度在255字符内")
        public String cover;

        @NotNull(message = "actionType不能为空")
        public NavMenuActionType actionType;

        @NotNull(message = "position不能为空")
        public NavMenuPosition position;

        @Min(value = 0, message = "sort在0-999范围内")
        @Max(value = 999, message = "sort在0-999范围内")
        public Integer sort = 0;

        @Size(max = 255, message = "description在255字符内")
        public String description = "";

//        @NotNull(message = "openPage不能为空")
        public NavMenuOpenPage openPage;

        @Max(value = 255, message = "openPageParam在255字符内")
        public String openPageParam;

        @Max(value = 255, message = "openUrl在255字符内")
        public String openUrl;

        public Long id;
    }
}
