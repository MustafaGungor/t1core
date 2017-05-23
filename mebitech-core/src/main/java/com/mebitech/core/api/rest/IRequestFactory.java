package com.mebitech.core.api.rest;

import com.mebitech.core.api.rest.requests.IPolicyExecutionRequest;
import com.mebitech.core.api.rest.requests.IPolicyRequest;
import com.mebitech.core.api.rest.requests.ITaskRequest;

/**
 * Interface for request factory. Request factories are used to create request
 * objects from given JSON string.
 * 
 *
 */
public interface IRequestFactory {

	IPolicyRequest createPolicyRequest(String json) throws Exception;

	ITaskRequest createTaskCommandRequest(String json) throws Exception;

	IPolicyExecutionRequest createPolicyExecutionRequest(String json) throws Exception;



}
