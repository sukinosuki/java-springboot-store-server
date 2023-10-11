package com.example.demo.admin.moduels.nav_menu_position;

import com.example.demo.admin.moduels.nav_menu_position.model.NavMenuPositionForm;
import com.example.demo.common.R;
import com.example.demo.model.NavMenuPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/nav-menu-position")
public class NavMenuPositionController {

    @Autowired
    private NavMenuPositionService navMenuPositionService;

    @PostMapping
    R add(@Valid @RequestBody @NotNull NavMenuPositionForm.Add form) {

        navMenuPositionService.add(form);

        return R.ok();
    }

    @PutMapping("/{id}")
    R<Void> update(@Valid @RequestBody @NotNull NavMenuPositionForm.Update form, @PathVariable Long id) {
        form.id = id;

        navMenuPositionService.update(form);

        return R.ok();
    }

    @DeleteMapping("/{id}")
    R<Void> delete(@PathVariable Long id) {
        navMenuPositionService.delete(id);

        return R.ok();
    }

    @GetMapping
    R<List<NavMenuPosition>> all() {
        List<NavMenuPosition> list = navMenuPositionService.all();

        return R.ok(list);
    }
}
