package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

import java.util.Map;

/**
 * Created by SedaOzcan on 9.02.2017.
 */
public interface IJobMaster extends IEntity<IEntitySecurity>{

    String toJson();

    Map<String, String> getProperties();

    void applyEntitySecurity(IEntitySecurity entitySecurity);

    Boolean getDeleted();

    String getGun();

    String getSaat();

    String getDakika();
}
