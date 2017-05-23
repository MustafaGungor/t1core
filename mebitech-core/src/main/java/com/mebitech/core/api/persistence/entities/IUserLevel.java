package com.mebitech.core.api.persistence.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

public interface IUserLevel extends IEntity<IEntitySecurity> {
    String toJson();

    Map<String, String> getProperties();

    void applyEntitySecurity(IEntitySecurity entitySecurity);

    Boolean getDeleted();

    IUser getUser();

    ILevel getLevel();

    void setUser(IUser user);

    void setLevel(ILevel level);

    List<? extends IGroup> getGroups();

    void setGroups(List<? extends IGroup> groups);

}