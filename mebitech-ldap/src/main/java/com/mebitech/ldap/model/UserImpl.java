package com.mebitech.ldap.model;

import java.util.ArrayList;
import java.util.List;


import com.mebitech.core.api.ldap.model.IUser;

/**
 * Default implementation for {@link IUser}
 * 
 *
 */
public class UserImpl implements IUser {

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private String surname;

	/**
	 * 
	 */
	private String uid;

	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}


}
