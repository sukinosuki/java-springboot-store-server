package com.example.demo.admin.moduels.sys_permission.model;

import com.example.demo.common.enums.SysPermissionMethod;
import com.example.demo.common.enums.SysPermissionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SysPermissionForm {

    @ToString
    public static class Add {
        @NotNull(message = "name不能为空")
        @NotEmpty(message = "name不能为空")
        public String name;

        @Size(max = 255, message = "description长度在255字符内")
        public String description;

        public Boolean enabled;

        @NotNull(message = "type不能为空")
        public SysPermissionType type;

        @NotNull(message = "method不能为空", groups = {NodeGroup.class})
        public SysPermissionMethod method;

        @NotNull(message = "url不能为空", groups = {NodeGroup.class})
        public String url;

        // node类型的permission(为了整理)需要放到menu下
        @NotNull(message = "pid不能为空", groups = {NodeGroup.class})
        public Long pid;

        @JsonIgnore
        public Long id;

        public static interface MenuGroup {
        }

        public static interface NodeGroup {

        }
    }

}
