package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

import java.util.Map;

public interface IIlce extends IEntity<IEntitySecurity> {
    String toJson();

    Map<String, String> getProperties();

    void applyEntitySecurity(IEntitySecurity entitySecurity);

    Boolean getDeleted();

    String getKodu();

    String getAdi();

    Map<String, Object> getIl();//json'a dönüştürürken sonsuz döngüye giriyordu

    IIl getIlObj();//java tarafında kullanabilmek için eklendi
}