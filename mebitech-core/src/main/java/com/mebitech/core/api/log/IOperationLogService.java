package com.mebitech.core.api.log;

import com.mebitech.core.api.persistence.entities.IOperationLog;
import com.mebitech.core.api.persistence.enums.CrudType;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

public interface IOperationLogService {

	/**
	 * 
	 * @param userId
	 * @param crudType
	 * @param message
	 * @param requestData
	 * @param requestIp
	 * @return
	 * @throws Exception
	 */
	IOperationLog saveLog(String userId, CrudType crudType, String message, byte[] requestData, String requestIp)
			throws Exception;

	/**
	 * 
	 * @param userId
	 * @param crudType
	 * @param taskId
	 * @param message
	 * @param requestData
	 * @param requestIp
	 * @return
	 * @throws Exception
	 */
	IOperationLog saveTaskLog(String userId, CrudType crudType, Long taskId, String message, byte[] requestData,
			String requestIp) throws Exception;

	/**
	 * 
	 * @param userId
	 * @param crudType
	 * @param policyId
	 * @param message
	 * @param requestData
	 * @param requestIp
	 * @return
	 * @throws Exception
	 */
	IOperationLog savePolicyLog(String userId, CrudType crudType, Long policyId, String message, byte[] requestData,
			String requestIp) throws Exception;

	/**
	 * 
	 * @param userId
	 * @param crudType
	 * @param profileId
	 * @param message
	 * @param requestData
	 * @param requestIp
	 * @return
	 * @throws Exception
	 */
	IOperationLog saveProfileLog(String userId, CrudType crudType, Long profileId, String message, byte[] requestData,
			String requestIp) throws Exception;


	/**
	 *
	 * @return
	 */
	IRestResponse list(IFilterAndPager filterAndPager);
}
