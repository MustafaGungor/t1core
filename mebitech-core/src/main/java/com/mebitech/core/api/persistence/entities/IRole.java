package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

/**
 * Created by tayipdemircan on 26.10.2016.
 */
public interface IRole extends IEntity<IEntitySecurity> {

    String getRoleCode();
    String getRoleName();
    Boolean getDeleted();
}
