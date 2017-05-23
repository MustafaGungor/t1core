package com.mebitech.core.api.ldap.model;

/**
 * Interface defines a user
 * 
 * 
 */
public interface IUser {

	/**
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * 
	 * @return name
	 */
	String getName();

	/**
	 * 
	 * @return surname
	 */
	String getSurname();

	/**
	 * 
	 * @param surname
	 */
	void setSurname(String surname);

	/**
	 * 
	 * @return UID/JID
	 */
	String getUid();

}
