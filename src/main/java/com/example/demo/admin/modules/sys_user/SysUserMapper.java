package com.example.demo.admin.modules.sys_user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.admin.modules.sys_user.model.SysUserForm;
import com.example.demo.admin.modules.sys_user.model.SysUserResSimple;
import com.example.demo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// TIP: mybatis xml 使用内部类: https://stackoverflow.com/questions/28855867/mybatis-does-not-resolve-inner-class-when-parsing-sql-mapper-configuration-handl
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUserResSimple> all(@Param("query") SysUserForm.Query query);

    SysUserResSimple detail(@Param("id") Long id);
}
