package com.mebitech.core.api.rest.requests;

import java.util.List;

/**
 * Request class for policy CRUD operations.
 * 
 *
 */
public interface IPolicyRequest extends IRequest {

	Long getId();

	String getLabel();

	String getDescription();

	boolean isActive();

	List<Long> getProfileIdList();

}
