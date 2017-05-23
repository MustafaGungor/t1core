package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.EnumUtil;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

/**
 * Created by tayipdemircan on 24.10.2016.
 */
public interface IUser extends IEntity<IEntitySecurity> {

    Boolean getActive();
    String getUserName();
    String getPassword();
    String getName();
    String getSurname();
    String getEmail();
//    IRole getRole();
//    List<? extends IGroup> getGroups();
//    void setGroups(List<? extends IGroup> groups);
    Boolean getDeleted();
    void setActive(Boolean b);

//    EnumUtil.KullaniciTipi getKullaniciTipi();
//EnumUtil.KullaniciTipi getKullaniciTipi();
    String toJson();
}
