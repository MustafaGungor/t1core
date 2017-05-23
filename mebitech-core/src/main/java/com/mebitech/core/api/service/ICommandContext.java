package com.mebitech.core.api.service;

import com.mebitech.core.api.plugin.ICommand;
import com.mebitech.core.api.rest.requests.ICommandRequest;
import com.mebitech.core.api.rest.requests.ITaskRequest;

/**
 * 
 * Provides request data context to {@link ICommand#execute(ICommandContext)}
 *
 * 
 */
public interface ICommandContext {

	/**
	 * @return {@link ICommandRequest} that fired this command
	 */
	ITaskRequest getRequest();

}
