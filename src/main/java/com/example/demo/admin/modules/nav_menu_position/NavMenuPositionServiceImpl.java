package com.example.demo.admin.modules.nav_menu_position;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.admin.modules.nav_menu_position.model.NavMenuPositionForm;
import com.example.demo.common.AppException;
import com.example.demo.model.NavMenuPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class NavMenuPositionServiceImpl extends ServiceImpl<NavMenuPositionMapper, NavMenuPosition> implements NavMenuPositionService {
    @Override
    public void add(NavMenuPositionForm.Add form) {

        NavMenuPosition navMenuPosition = new NavMenuPosition();

        navMenuPosition.name = form.name;
        navMenuPosition.description = form.description == null ? "" : form.description;
        navMenuPosition.value = form.value;
        navMenuPosition.sort = form.sort == null ? 0 : form.sort;

        boolean ok = this.save(navMenuPosition);
        if (!ok) {
            throw AppException.actionFailError(null);
        }
    }

    @Override
    public void update(NavMenuPositionForm.Update form) {

//        LambdaUpdateWrapper<Object> uw = new LambdaUpdateWrapper<>();
//        LambdaUpdateWrapper<Object> uw = new UpdateWrapper<>().lambda();
        // TODO
        LambdaUpdateWrapper<NavMenuPosition> uw = new LambdaUpdateWrapper<>();
        uw.eq(NavMenuPosition::getId, form.id)
                .set(NavMenuPosition::getName, form.name)
                .set(NavMenuPosition::getUpdatedAt, LocalDateTime.now());

        if (form.sort != null) {
            uw.set(NavMenuPosition::getSort, form.sort);
        }
        if (form.description != null) {
            uw.set(NavMenuPosition::getDescription, form.description);
        }

        boolean ok = this.update(uw);
        if (!ok) {
            throw AppException.actionFailError(null);
        }
    }

    @Override
    public void delete(Long id) {
        boolean ok = this.removeById(id);

        if (!ok) {
            throw AppException.actionFailError(null);
        }
    }

    @Override
    public List<NavMenuPosition> all() {
        return this.list();
    }
}
