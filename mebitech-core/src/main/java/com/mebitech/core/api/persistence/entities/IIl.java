package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

import java.util.List;
import java.util.Map;

public interface IIl extends IEntity<IEntitySecurity> {
    String toJson();

    Map<String, String> getProperties();

    void applyEntitySecurity(IEntitySecurity entitySecurity);

    Boolean getDeleted();

    String getKodu();

    String getAdi();

    List<? extends IIlce> getIlceList();

    Map<String, Object> getUlke();

    IUlke getUlkeObj();
}