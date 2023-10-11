package com.example.demo.admin.moduels.nav_menu_position;

import com.example.demo.admin.moduels.nav_menu_position.model.NavMenuPositionForm;
import com.example.demo.model.NavMenuPosition;

import java.util.List;

public interface NavMenuPositionService {

    void add(NavMenuPositionForm.Add form);

    void update(NavMenuPositionForm.Update form);

    void delete(Long id);

    List<NavMenuPosition> all();
}
