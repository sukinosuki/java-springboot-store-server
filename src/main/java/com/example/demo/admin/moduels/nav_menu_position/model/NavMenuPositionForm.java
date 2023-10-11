package com.example.demo.admin.moduels.nav_menu_position.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

public class NavMenuPositionForm {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Add {
        @NotNull(message = "name不能为空")
        @NotEmpty(message = "name不能为空")
        @Size(max = 50, message = "name在50字符内")
        public String name;

        @NotNull(message = "value不能为空")
        @Size(max = 50, message = "value在50字符内")
        public String value;

        @Size(max = 255, message = "description在255字符内")
        public String description;

        @Max(value = 999, message = "sort值在0-999间")
        @Min(value = 0, message = "sort值在0-999间")
        public Integer sort;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @NotNull(message = "name不能为空")
        @NotEmpty(message = "name不能为空")
        @Size(max = 50, message = "name在50字符内")
        public String name;

        @Size(max = 255, message = "description在255字符内")
        public String description;

        @Max(value = 999, message = "sort值在0-999间")
        @Min(value = 0, message = "sort值在0-999间")
        public Integer sort;

        public Long id;
    }
}
