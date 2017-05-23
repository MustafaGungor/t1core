package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

import java.util.List;
import java.util.Map;

public interface IMenu extends IEntity<IEntitySecurity> {
    String toJson();

    Map<String, String> getProperties();

    void applyEntitySecurity(IEntitySecurity entitySecurity);

    Boolean getDeleted();

    String getText();

    String getPath();

    Integer getIndex();

    IMenu getParent();

    String getModule();

    String getIcon();

    List<IMenu> getItems();

    void setItems(List<IMenu> items);
}