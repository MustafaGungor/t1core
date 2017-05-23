package com.mebitech.core.api.rest.requests;

import java.util.Date;
import java.util.Map;

import com.mebitech.core.api.rest.requests.ICommandRequest;

/**
 * Request class used for task creation/execution.
 * 
 *
 */
public interface ITaskRequest extends ICommandRequest {

	String getPluginName();

	String getPluginVersion();

	String getCommandId();

	Map<String, Object> getParameterMap();

	String getCronExpression();

	Date getActivationDate();

}
