package com.mebitech.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mebitech.core.api.rest.IRequestFactory;
import com.mebitech.core.api.rest.requests.IPolicyExecutionRequest;
import com.mebitech.core.api.rest.requests.IPolicyRequest;
import com.mebitech.core.api.rest.requests.ITaskRequest;
import com.mebitech.service.requests.PolicyExecutionRequestImpl;
import com.mebitech.service.requests.PolicyRequestImpl;
import com.mebitech.service.requests.TaskRequestImpl;


/**
 * Default implementation for {@link IRequestFactory}.
 *
 */
public class RequestFactoryImpl implements IRequestFactory {

	private static Logger logger = LoggerFactory.getLogger(RequestFactoryImpl.class);

	
	@Override
	public IPolicyRequest createPolicyRequest(String json) throws Exception {
		logger.debug("Creating ProfileRequestImpl instance from json: {}", json);
		return new ObjectMapper().readValue(json, PolicyRequestImpl.class);
	}

	@Override
	public IPolicyExecutionRequest createPolicyExecutionRequest(String json) throws Exception {
		logger.debug("Creating PolicyExecutionImpl instance from json: {}", json);
		return new ObjectMapper().readValue(json, PolicyExecutionRequestImpl.class);
	}

	@Override
	public ITaskRequest createTaskCommandRequest(String json) throws Exception {
		logger.debug("Creating TaskRequestImpl instance from json: {}", json);
		return new ObjectMapper().readValue(json, TaskRequestImpl.class);
	}

}
