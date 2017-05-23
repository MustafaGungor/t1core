package com.mebitech.core.api.service;

import com.mebitech.core.api.rest.requests.ITaskRequest;

/**
 * 
 * Factory to create {@link ICommandContext}
 *
 * 
 */
public interface ICommandContextFactory {

	/**
	 * 
	 * @param request
	 * @return new command context from this request
	 */
	ICommandContext create(ITaskRequest request);

}
