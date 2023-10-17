package com.example.demo.admin.modules.sys_user.model;

import com.example.demo.common.BaseQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class SysUserForm {

    public static class Add {
        @NotNull(message = "username不能为空", groups = {AddGroup.class})
        @NotEmpty(message = "username不能为空", groups = {AddGroup.class})
        @Size(min = 4, max = 12, message = "username在4-12字符内", groups = {AddGroup.class})
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "username为英文字符或数字", groups = {AddGroup.class})
        public String username;

        @NotNull(message = "password不能为空")
        @NotEmpty(message = "password不能为空")
        @Size(min = 4, max = 12, message = "password在4-12字符内")
        public String password;

        @Size(max = 255, message = "avatar在255字符内")
        public String avatar = "21";

        @NotEmpty(message = "roleIds不能为空")
        @NotNull(message = "roleIds不能为空")
        @Size(max = 10, message = "用户角色不能超过10个")
        public List<Long> roleIds;

        public Boolean enabled;

        @JsonIgnore
        public Long id;

        public static interface AddGroup {
        }

        public static interface UpdateGroup {
        }
    }

    public static class Query extends BaseQuery {

    }
}
