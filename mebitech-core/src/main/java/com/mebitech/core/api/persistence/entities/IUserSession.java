package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.persistence.enums.SessionEvent;

/**
 * IUserSession entity class is responsible for storing user login/logout
 * events.
 * 
 *
 */
public interface IUserSession extends IEntity {

	/**
	 * 
	 * @return
	 */
	String getUsername();

	/**
	 * 
	 * @return
	 */
	SessionEvent getSessionEvent();

}
