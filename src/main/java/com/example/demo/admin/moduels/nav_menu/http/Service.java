package com.example.demo.admin.moduels.nav_menu.http;

import com.example.demo.admin.moduels.nav_menu.model.NavMenuForm;
import com.example.demo.model.NavMenu;

import java.util.List;

public interface Service {

    void add(NavMenuForm.Add form);

    void update(NavMenuForm.Add form);

    void delete(List<Long> ids);

    List<NavMenu> all();

}
