package com.example.demo.admin.modules.goods.model;

import com.example.demo.common.BaseQuery;
import com.example.demo.common.enums.QueryOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class GoodsForm {

    public static class Add {
        @NotNull(message = "name不能为空")
        @NotEmpty(message = "name不能为空")
        @Size(max = 50, message = "name长度在50字符内")
        public String name;

        @Max(value = 999, message = "sort在0-999范围内")
        @Min(value = 0, message = "sort在0-999范围内")
        public Integer sort;


        @NotNull(message = "price不能为空")
        @Max(value = 99999, message = "price在0.01-99999范围内")
        @Min(value = 0, message = "price在0.01-99999范围内")
        public BigDecimal price;

        @Size(max = 255, message = "description在255字符内")
        public String description;

        @NotNull(message = "stock不能为空")
        @Max(value = 99999, message = "stock在0-99999范围内")
        @Min(value = 0, message = "stock在0-99999范围内")
        public Integer stock;

        @Size(max = 1000, message = "description在1000字符内")
        public String detail;

        @NotNull(message = "categoryIds不能为空")
        @Size(min = 1, max = 3, message = "商品分类在1-3个范围内")
        public List<Long> categoryIds;

        @NotNull(message = "onShelf不能为空")
        public Boolean onShelf;

        @Min(value = 0, message = "fakeBaseSales在0-99999范围内")
        @Max(value = 99999, message = "fakeBaseSales在0-99999范围内")
        public Integer fakeBaseSales;

        @NotNull(message = "covers不能为空")
        @Size(min = 1, max = 5, message = "封面图在1-5张范围内")
        public List<String> covers;

        @JsonIgnore
        public Long id;
    }

    public static class Query extends BaseQuery {
        public Long categoryId;

//        public QueryOrder order;
//
//        public String orderField;
    }
}
