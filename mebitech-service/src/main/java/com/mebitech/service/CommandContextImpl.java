package com.mebitech.service;

import com.mebitech.core.api.rest.requests.ITaskRequest;
import com.mebitech.core.api.service.ICommandContext;

public class CommandContextImpl implements ICommandContext {

	private ITaskRequest request;

	public CommandContextImpl(ITaskRequest request) {
		this.request = request;
	}

	@Override
	public ITaskRequest getRequest() {
		return this.request;
	}

}
