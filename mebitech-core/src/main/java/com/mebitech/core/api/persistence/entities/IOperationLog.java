package com.mebitech.core.api.persistence.entities;

import com.mebitech.core.api.persistence.enums.CrudType;

/**
 * IOperationLog entity class is responsible for storing system-wide log
 * records.
 * 
 *
 */
public interface IOperationLog extends IEntity {

	/**
	 * 
	 * @return LDAO UID of the user
	 */
	String getUserId();

	/**
	 * 
	 * @return CRUD type
	 */
	CrudType getCrudType();

	/**
	 * 
	 * @return task ID
	 */
	Long getTaskId();

	/**
	 * 
	 * @return policy ID
	 */
	Long getPolicyId();

	/**
	 * 
	 * @return profile ID
	 */
	Long getProfileId();

	/**
	 * 
	 * @return log message
	 */
	String getLogMessage();

	/**
	 * 
	 * @return binary representation of data in received request
	 */
	byte[] getRequestData();

	/**
	 * 
	 * @return sender IP
	 */
	String getRequestIp();

	Boolean getDeleted();

}
