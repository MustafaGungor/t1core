package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

import java.util.Map;

public interface IPermission extends IEntity<IEntitySecurity> {
    String toJson();

    Map<String, String> getProperties();

    void applyEntitySecurity(IEntitySecurity entitySecurity);

    Boolean getDeleted();

    String getFormId();

    String getModuleId();

    String getPermissionId();

    void setDeleted(Boolean deleted);

    void setFormId(String formId);

    void setModuleId(String moduleId);

    void setPermissionId(String permissionId);

    IUserLevel getUserLevel();

    IGroup getGroup();
}