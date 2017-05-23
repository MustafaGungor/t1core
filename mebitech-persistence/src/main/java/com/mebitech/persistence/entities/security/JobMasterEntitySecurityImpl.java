package com.mebitech.persistence.entities.security;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;

/**
 * Created by SedaOzcan on 10.02.2017.
 */
public class JobMasterEntitySecurityImpl implements IEntitySecurity{

    private CrudType[] crudTypes;
    private String[] entityAttributes;
    @Override
    public void addCrudType(CrudType[] crudType) {
    }
    @Override
    public void addEntityAttributes(String[] attirubeName) {
    }
    @Override
    public CrudType[] getCrudType() {
        return this.crudTypes;
    }
    @Override
    public String[] getEntityAttributes() {
        return this.entityAttributes;
    }
}
