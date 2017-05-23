package com.mebitech.service;

import java.util.List;
import java.util.Map;

import com.mebitech.core.api.plugin.ICommand;
import com.mebitech.core.api.service.ICommandResult;
import com.mebitech.core.api.service.ICommandResultFactory;
import com.mebitech.core.api.service.enums.CommandResultStatus;

/**
 * 
 *
 */
public class CommandResultFactoryImpl implements ICommandResultFactory {

	@Override
	public ICommandResult create(CommandResultStatus status, List<String> messages, ICommand command) {
		return new CommandResultImpl(status, messages, command);
	}

	@Override
	public ICommandResult create(CommandResultStatus status, List<String> messages, ICommand command,
			Map<String, Object> resultMap) {
		return new CommandResultImpl(status, messages, command, resultMap);
	}

}
