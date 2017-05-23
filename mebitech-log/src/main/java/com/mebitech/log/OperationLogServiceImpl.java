package com.mebitech.log;

import com.mebitech.core.api.log.IOperationLogService;
import com.mebitech.core.api.persistence.dao.IOperationLogDao;
import com.mebitech.core.api.persistence.entities.IOperationLog;
import com.mebitech.core.api.persistence.enums.CrudType;
import com.mebitech.core.api.persistence.factories.IEntityFactory;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.responses.IRestResponse;

import java.util.Map;

/**
 * 
 *
 */
public class OperationLogServiceImpl implements IOperationLogService {

	private IOperationLogDao logDao;
	private IEntityFactory entityFactory;
	private IResponseFactory responseFactory;


	@Override
	public IRestResponse list(IFilterAndPager filterAndPager){

		Map<String, Object> retMap = logDao.findByFilters(filterAndPager);

		 return responseFactory.createResponse(RestResponseStatus.OK, "Success", retMap, retMap.size());
	}

	@Override
	public IOperationLog saveLog(String userId, CrudType crudType, String message, byte[] requestData, String requestIp)
			throws Exception {
		if(requestData == null)
			requestData = message.getBytes("UTF-8");
		IOperationLog log = entityFactory.createLog(userId, crudType, null, null, null, message, requestData,
				requestIp);
		log = (IOperationLog) logDao.save(log);
		return log;
	}

	@Override
	public IOperationLog saveTaskLog(String userId, CrudType crudType, Long taskId, String message, byte[] requestData,
			String requestIp) throws Exception {
		IOperationLog log = entityFactory.createLog(userId, crudType, taskId, null, null, message, requestData,
				requestIp);
		log = (IOperationLog) logDao.save(log);
		return log;
	}

	@Override
	public IOperationLog savePolicyLog(String userId, CrudType crudType, Long policyId, String message,
			byte[] requestData, String requestIp) throws Exception {
		IOperationLog log = entityFactory.createLog(userId, crudType, null, policyId, null, message, requestData,
				requestIp);
		log = (IOperationLog) logDao.save(log);
		return log;
	}

	@Override
	public IOperationLog saveProfileLog(String userId, CrudType crudType, Long profileId, String message,
			byte[] requestData, String requestIp) throws Exception {
		IOperationLog log = entityFactory.createLog(userId, crudType, null, null, profileId, message, requestData,
				requestIp);
		log = (IOperationLog) logDao.save(log);
		return log;
	}

	/*
	 * Service setters
	 */

	/**
	 * 
	 * @param logDao
	 */
	public void setLogDao(IOperationLogDao logDao) {
		this.logDao = logDao;
	}

	/**
	 * 
	 * @param entityFactory
	 */
	public void setEntityFactory(IEntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	public void setResponseFactory(IResponseFactory responseFactory) {
		this.responseFactory = responseFactory;
	}
}
