package com.mebitech.persistence.dao;

import com.mebitech.core.api.persistence.dao.IMenuDao;
import com.mebitech.persistence.entities.MenuImpl;

@SuppressWarnings("unchecked")
public class MenuDaoImpl extends ABaseDao<MenuImpl> implements IMenuDao {
    public MenuDaoImpl() {
        super(MenuImpl.class);
    }
}
