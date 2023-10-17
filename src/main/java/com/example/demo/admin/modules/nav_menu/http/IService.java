package com.example.demo.admin.modules.nav_menu.http;

import com.example.demo.admin.modules.nav_menu.model.NavMenuForm;
import com.example.demo.model.NavMenu;

import java.util.List;

interface IService {

    void add(NavMenuForm.Add form);

    void update(NavMenuForm.Add form);

    void delete(List<Long> ids);

    List<NavMenu> all();

}
