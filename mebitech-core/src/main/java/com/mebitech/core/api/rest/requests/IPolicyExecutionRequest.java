package com.mebitech.core.api.rest.requests;

import java.util.Date;

/**
 * Request class used for policy execution.
 * 
 *
 */
public interface IPolicyExecutionRequest extends ICommandRequest {

	Long getId();

	Date getActivationDate();
}
