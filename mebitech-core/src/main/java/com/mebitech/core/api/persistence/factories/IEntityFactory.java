package com.mebitech.core.api.persistence.factories;

import com.mebitech.core.api.persistence.entities.IOperationLog;
import com.mebitech.core.api.persistence.entities.IUserSession;
import com.mebitech.core.api.persistence.enums.CrudType;
import com.mebitech.core.api.persistence.enums.SessionEvent;

/**
 * Factory class for all entities.
 * 
 *
 */
public interface IEntityFactory {

	/**
	 * 
	 * @param username
	 * @param sessionEvent
	 * @return
	 */
	IUserSession createUserSession(String username, SessionEvent sessionEvent);
	
	/**
	 * 
	 * @param userId
	 * @param crudType
	 * @param taskId
	 * @param policyId
	 * @param profileId
	 * @param message
	 * @param requestData
	 * @param requestIp
	 * @return
	 */
	IOperationLog createLog(String userId, CrudType crudType, Long taskId, Long policyId, Long profileId,
			String message, byte[] requestData, String requestIp);


}
