package com.example.demo.admin.moduels.nav_menu.http;

import com.example.demo.admin.moduels.nav_menu.model.NavMenuForm;
import com.example.demo.common.R;
import com.example.demo.common.enums.NavMenuActionType;
import com.example.demo.common.enums.NavMenuOpenPage;
import com.example.demo.common.enums.NavMenuPosition;
import com.example.demo.model.NavMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController(value = "NavMenuController")
@RequestMapping("/admin/api/v1/nav-menu")
@Slf4j
public class Controller {

    @Autowired
    IService IService;

    @PostMapping
    R<Void> add(@Valid @RequestBody @NotNull NavMenuForm.Add form) {
        log.info("添加 nav menu, form: ".concat(form.toString()));

        IService.add(form);

        return R.ok();
    }

    @PutMapping("/{id}")
    R<Void> update(@Valid @RequestBody @NotNull NavMenuForm.Add form, @PathVariable Long id) {

        form.id = id;

        IService.update(form);

        return R.ok();
    }

    @GetMapping
    R get(@RequestParam("position") NavMenuPosition position,
          @RequestParam(value = "page", required = false) Integer page,
          @RequestParam(value = "size", required = false) Integer size,
          @RequestParam("actionType") NavMenuActionType actionType,
          @RequestParam("openPage") NavMenuOpenPage openPage) {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("position", position);

        hashMap.put("actionType", actionType);
        hashMap.put("openPage", openPage);
        hashMap.put("page", page);
        hashMap.put("size", size);

        List<NavMenu> list = IService.all();

        return R.ok(list);
    }

    @DeleteMapping("/{id}")
    R delete(@PathVariable Long id) {
        IService.delete(List.of(id));

        return R.ok();
    }
}
