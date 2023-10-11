package com.example.demo.admin.moduels.nav_menu;

import com.example.demo.admin.moduels.nav_menu.model.NavMenuForm;
import com.example.demo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/api/v1/nav-menu")
@Slf4j
public class NavMenuController {

    @PostMapping
    R add(@Valid @RequestBody @NotNull NavMenuForm.Add form) {
        log.info("添加 nav menu, form: ".concat(form.toString()));

        return R.ok(form);
    }
}
