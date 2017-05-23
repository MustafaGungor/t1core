package com.mebitech.core.api.service;

import java.util.List;
import java.util.Map;

import com.mebitech.core.api.plugin.ICommand;
import com.mebitech.core.api.service.enums.CommandResultStatus;

/**
 * 
 * Keeps information about a Command execution result
 * 
 */
public interface ICommandResult {

	/**
	 * 
	 * @return command result status {@link CommandResultStatus}
	 */
	CommandResultStatus getStatus();

	/**
	 * 
	 * @return list of info text provided by
	 *         {@link ICommand#execute(ICommandContext)}
	 */
	List<String> getMessages();

	/**
	 * 
	 * @return map containing values provided by
	 *         {@link ICommand#execute(ICommandContext)}
	 */
	Map<String, Object> getResultMap();

	/**
	 * 
	 * @return ICommand that created this specific result
	 */
	ICommand getCommand();

}
