package com.mebitech.core.api.rest.exceptions;

import com.mebitech.core.api.rest.requests.IRequest;

public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = 3378691828847193601L;

	public InvalidRequestException(IRequest request) {
		super("No command registered for REST URL: " + request);
	}

}
