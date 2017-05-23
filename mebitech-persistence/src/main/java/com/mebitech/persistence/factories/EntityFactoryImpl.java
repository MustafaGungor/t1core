package com.mebitech.persistence.factories;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

import com.mebitech.core.api.ldap.model.LdapEntry;

import com.mebitech.core.api.persistence.entities.IOperationLog;
import com.mebitech.core.api.persistence.entities.IUserSession;
import com.mebitech.core.api.persistence.enums.CrudType;
import com.mebitech.core.api.persistence.enums.SessionEvent;
import com.mebitech.core.api.persistence.factories.IEntityFactory;
import com.mebitech.core.api.rest.requests.ICommandRequest;
import com.mebitech.core.api.rest.requests.IPolicyExecutionRequest;
import com.mebitech.core.api.rest.requests.IPolicyRequest;

import com.mebitech.core.api.rest.requests.ITaskRequest;

import com.mebitech.persistence.entities.OperationLogImpl;

import com.mebitech.persistence.entities.UserSessionImpl;

/**
 * Default implementation for {@link IEntityFactory}.
 * 
 *
 */
public class EntityFactoryImpl implements IEntityFactory {

	@Override
	public IOperationLog createLog(String userId, CrudType crudType, Long taskId, Long policyId, Long profileId,
			String message, byte[] requestData, String requestIp) {
		return new OperationLogImpl(null, userId, crudType, taskId, policyId, profileId, message, requestData,
				requestIp, new Date());
	}

	@Override
	public IUserSession createUserSession(String username, SessionEvent sessionEvent) {
		return new UserSessionImpl(null, username, sessionEvent, new Date());
	}

	
}
