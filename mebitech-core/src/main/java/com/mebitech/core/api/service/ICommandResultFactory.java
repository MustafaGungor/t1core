package com.mebitech.core.api.service;

import java.util.List;
import java.util.Map;

import com.mebitech.core.api.plugin.ICommand;
import com.mebitech.core.api.service.enums.CommandResultStatus;

/**
 * 
 * Factory to create {@link ICommandResult}
 *
 * 
 */
public interface ICommandResultFactory {

	/**
	 * 
	 * @param status
	 *            of command result
	 * @param messages
	 *            in command result
	 * @param command
	 *            creating command result
	 * @return new command result
	 */
	ICommandResult create(CommandResultStatus status, List<String> messages, ICommand command);

	/**
	 * @param status
	 *            of command result
	 * @param messages
	 *            in command result
	 * @param command
	 *            creating this command result
	 * @param resultMap
	 *            containing command execution results
	 * @return new command result
	 */
	ICommandResult create(CommandResultStatus status, List<String> messages, ICommand command,
			Map<String, Object> resultMap);

}
