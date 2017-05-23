package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.IMenuDao;
import com.mebitech.core.api.persistence.entities.IMenu;
import com.mebitech.core.api.persistence.filter.IFilter;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.processors.IMenuRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuRequestProcessorImpl extends BaseRequestProcessor implements IMenuRequestProcessor {

    private IMenuDao menuDao;

    @Override
    public IRestResponse list(String hostname, String dn, String uid) {
        return null;
    }

    @Override
    public IRestResponse add(IMenu role) {
        IMenu rolec = null;
        try {
            rolec = (IMenu) menuDao.save(role);
            createResponse(rolec);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse update(IMenu role) {
        IMenu rolec = null;
        try {
            rolec = (IMenu) menuDao.update(role);
            createResponse(rolec);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse delete(Long id) {
        try {
            menuDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = menuDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    @Override
    public IRestResponse findById(Long id) {
        createResponse(menuDao.find(id));
        return getResponse();
    }

    @Override
    public IRestResponse getProperties() {
        createResponse(menuDao.getProperties());
        return getResponse();
    }

    @Override
    public IRestResponse getMenuList(){
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("index",-1);
        List menuList = menuDao.findByProperties(properties,null,0);
        IMenu rootMenu = (IMenu) menuList.get(0);
        loadSubMenus(rootMenu);
//        properties = new HashMap<String, Object>();
//        properties.put("index",0);
//        menuList = menuDao.findByProperties(properties,null,0);
//        rootMenu.setItems(new ArrayList<IMenu>());
//        for (int i = 0; i < menuList.size() ; i++) {
//            IMenu menu = (IMenu) menuList.get(i);
//            loadSubMenus(menu);
//            rootMenu.getItems().add(menu);
//        }
        createResponse(rootMenu);
        return getResponse();
    }

    private void loadSubMenus(IMenu parentMenu){
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("parent.id",parentMenu.getId());
        List menuList = menuDao.findByProperties(properties,null,0);
        if (parentMenu.getItems() == null)
            parentMenu.setItems(new ArrayList<IMenu>());
        if(menuList != null && menuList.size() > 0) {
            for (int i = 0; i < menuList.size(); i++) {
                IMenu menu = (IMenu) menuList.get(i);
                loadSubMenus(menu);
                parentMenu.getItems().add(menu);
            }
        }
    }

    public void setMenuDao(IMenuDao menuDao) {
        this.menuDao = menuDao;
    }

}