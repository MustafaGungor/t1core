package com.mebitech.persistence.dao;

import com.mebitech.persistence.entities.UserImpl;

/**
 * Created by tayipdemircan on 14.11.2016.
 */
public class TempDao extends ABaseDao<UserImpl> {

    public TempDao(Class<UserImpl> typedClass) {
        super(typedClass);
    }
}
