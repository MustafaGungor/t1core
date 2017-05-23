package com.mebitech.persistence.dao;

import com.mebitech.core.api.persistence.dao.IGroupDao;
import com.mebitech.persistence.entities.GroupImpl;

/**
 * Created by tayipdemircan on 28.10.2016.
 */
public class GroupDaoImpl extends ABaseDao<GroupImpl> implements IGroupDao {
    public GroupDaoImpl() {//Class<GroupImpl> typedClass
        super(GroupImpl.class);
    }
}
