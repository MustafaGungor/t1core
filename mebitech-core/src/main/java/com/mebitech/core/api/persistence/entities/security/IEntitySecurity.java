package com.mebitech.core.api.persistence.entities.security;

import com.mebitech.core.api.persistence.enums.CrudType;

/**
 * Base entity security class for all entities.
 * 
 *
 */
public interface IEntitySecurity {

	
	void addCrudType(CrudType[] crudType);
	
	void addEntityAttributes(String[] attirubeName);
	
	
	CrudType[] getCrudType();
	
	String[] getEntityAttributes();
}
