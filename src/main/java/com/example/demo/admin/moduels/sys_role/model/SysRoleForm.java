package com.example.demo.admin.moduels.sys_role.model;

import com.example.demo.common.enums.QueryOrder;
import com.example.demo.util.validation.NotEmptyFields;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class SysRoleForm {

    @ToString
    public static class Add {
        @NotNull(message = "name不能为空")
        @NotEmpty(message = "name不能为空")
        public String name;

        public Boolean enabled;

        @Size(max = 255, message = "description长度在255字符内")
        public String description;

        @NotNull(message = "roleIds不能为空")
        @NotEmpty(message = "roleIds不能为空")
        public List<Long> roleIds;

        @JsonIgnore
        public Long id;
    }

    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Query{
        public Long page;
        public Long size;
        public QueryOrder order;
        public String orderField;
    }
}
