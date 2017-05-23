package com.mebitech.core.api.rest.requests;

import java.util.List;

import com.mebitech.core.api.rest.enums.DNType;

/**
 * Base request for ICommand related objects.
 * 
 *
 */
public interface ICommandRequest extends IRequest {

	List<String> getDnList();

	DNType getDnType();

}
