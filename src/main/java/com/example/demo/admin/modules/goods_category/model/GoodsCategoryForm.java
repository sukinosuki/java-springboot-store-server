package com.example.demo.admin.modules.goods_category.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;

public class GoodsCategoryForm {

    public static class Add{
        @NotEmpty(message = "name不能为空")
        @NotNull(message = "name不能为空")
        @Size(max = 50, message = "name长度在50字符内")
        public String name;

        @Max(value = 999, message = "sort在0-999之间")
        @Min(value = 0, message = "sort在0-999之间")
        public Integer sort;

        public Boolean enabled;

        public Long pid;

        @JsonIgnore
        public Long id;
    }
}
