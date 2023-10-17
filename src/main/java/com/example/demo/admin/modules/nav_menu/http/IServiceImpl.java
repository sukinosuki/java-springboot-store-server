package com.example.demo.admin.modules.nav_menu.http;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.demo.admin.modules.nav_menu.NavMenuDao;
import com.example.demo.admin.modules.nav_menu.NavMenuMapper;
import com.example.demo.admin.modules.nav_menu.model.NavMenuForm;
import com.example.demo.common.AppException;
import com.example.demo.model.NavMenu;
import com.example.demo.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("navMenuService")
public class IServiceImpl implements IService {
    @Autowired
    NavMenuMapper navMenuMapper;

    @Autowired
    NavMenuDao navMenuDao;

    @Override
    public void add(NavMenuForm.Add form) {

        NavMenu navMenu = new NavMenu();
        navMenu.name = form.name;
        navMenu.cover = form.cover == null ? "" : form.cover;
        navMenu.actionType = form.actionType;
        navMenu.openPage = form.openPage;
        navMenu.openPageParam = form.openPageParam == null ? "" : form.openPageParam;
        navMenu.openUrl = form.openUrl == null ? "" : form.openUrl;
        navMenu.sort = form.sort;
        navMenu.position = form.position;
        navMenu.description = form.description == null ? "" : form.description;

        navMenuDao.save(navMenu);
    }

    @Override
    public void update(NavMenuForm.Add form) {
        LambdaUpdateWrapper<NavMenu> uw = new LambdaUpdateWrapper<>();

        uw.eq(NavMenu::getId, form.id)
                .set(NavMenu::getDescription, form.description)
                .set(NavMenu::getName, form.name)
                .set(NavMenu::getActionType, form.actionType)
                .set(NavMenu::getOpenPage, form.openPage)
                .set(NavMenu::getOpenUrl, form.openUrl)
                .set(NavMenu::getOpenPageParam, NullUtil.null2String(form.openPageParam))
                .set(NavMenu::getSort, form.sort)
                .set(NavMenu::getDescription, form.description);

        boolean ok = navMenuDao.update(uw);

        if (!ok) {
            throw AppException.actionFailError(null);
        }
    }

    @Override
    public void delete(List<Long> ids) {
        boolean ok = navMenuDao.removeBatchByIds(ids);

        if (!ok) {
            throw AppException.actionFailError(null);
        }
    }

    @Override
    public List<NavMenu> all() {
        return navMenuDao.list();
    }
}
