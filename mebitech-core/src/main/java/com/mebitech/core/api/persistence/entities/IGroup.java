package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

import java.util.List;

/**
 * Created by tayipdemircan on 28.10.2016.
 */
public interface IGroup extends IEntity<IEntitySecurity> {

    String getGroupCode();
    String getGroupName();
    Boolean getDeleted();
    //List<? extends IUser> getUsers();
}
