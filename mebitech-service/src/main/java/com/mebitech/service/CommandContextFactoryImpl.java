package com.mebitech.service;

import com.mebitech.core.api.rest.requests.ITaskRequest;
import com.mebitech.core.api.service.ICommandContext;
import com.mebitech.core.api.service.ICommandContextFactory;

public class CommandContextFactoryImpl implements ICommandContextFactory {

	@Override
	public ICommandContext create(ITaskRequest request) {
		return new CommandContextImpl(request);
	}
}
