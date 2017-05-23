package com.mebitech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.requests.IRequest;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.core.api.service.ICommandResult;
import com.mebitech.service.responses.RestResponseImpl;

/**
 * Default implementation for {@link IResponseFactory}
 * 
 *
 */
public class ResponseFactoryImpl implements IResponseFactory {

	@Override
	public IRestResponse createResponse(RestResponseStatus status, List<String> messages) {
		return new RestResponseImpl(status, messages, null);
	}
	
	@Override
	public IRestResponse createResponse(IRequest request, RestResponseStatus status, List<String> messages) {
		return new RestResponseImpl(status, messages, null);
	}
	
	@Override
	public IRestResponse createResponse(RestResponseStatus status, List<String> messages, Map<String, Object> resultMap) {
		return new RestResponseImpl(status, messages, resultMap);
	}
	
	@Override
	public IRestResponse createResponse(IRequest request, RestResponseStatus status, List<String> messages, Map<String, Object> resultMap) {
		return new RestResponseImpl(status, messages, resultMap);
	}

	@Override
	public IRestResponse createResponse(RestResponseStatus status, String message) {
		List<String> messages = new ArrayList<String>();
		messages.add(message);
		return new RestResponseImpl(status, messages, null);
	}

	@Override
	public IRestResponse createResponse(RestResponseStatus status, String message, Map<String, Object> resultMap) {
		List<String> messages = new ArrayList<String>();
		messages.add(message);
		return new RestResponseImpl(status, messages, resultMap);
	}
	
	@Override
	public IRestResponse createResponse(ICommandResult result) {
		return new RestResponseImpl(result);
	}

	@Override
	public IRestResponse createResponse(RestResponseStatus status, String message, Map<String, Object> resultMap, int count) {
		List<String> messages = new ArrayList<String>();
		messages.add(message);
		return new RestResponseImpl(status, messages, resultMap, count);
	}
}
