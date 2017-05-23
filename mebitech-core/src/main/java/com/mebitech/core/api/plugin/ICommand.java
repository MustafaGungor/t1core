package com.mebitech.core.api.plugin;

import com.mebitech.core.api.service.ICommandContext;
import com.mebitech.core.api.service.ICommandResult;

/**
 * 
 * <p>
 * This is the interface for server-side plugin command implementations. Any
 * class implementing this interface can deploy a new command to the server.
 * ServiceRouterImpl directs RestRequestImpl to the appropriate command if
 * exists according to respective properties.
 * </p>
 * <br/>
 * 
 * <p>
 * ServiceRouterImpl tries to find the appropriate command via
 * ServiceRegistryImpl which keeps ICommand instances according to some specific
 * key format. This format consist of these properties: <br/>
 * 
 * {PLUGIN_NAME}:{PLUGIN_VERSION}:{COMMAND_ID}
 * 
 * The first two properties are the plugin name and plugin version respectively,
 * and the third property is a unique identifier of the command for its plugin.
 * </p>
 *
 * 
 */
public interface ICommand {

	/**
	 * Any custom plugin command should implement this method and return a
	 * non-null {@link ICommandResult}, A command may access to IAuthService,
	 * ILdapService and more plugin services provided by the core system to do
	 * its necessary job.
	 * 
	 * @return command result {@link ICommandResult}
	 * @throws Exception 
	 */
	ICommandResult execute(ICommandContext context) throws Exception;

	/**
	 * Any custom plugin command should implement this method and return a
	 * non-null {@link ICommandResult}, It should check whether the arguments
	 * are valid.
	 * 
	 * @return command result {@link ICommandResult}
	 */
	ICommandResult validate(ICommandContext context);

	/**
	 * 
	 * @return id of the plugin implementing this command.
	 */
	String getPluginName();

	/**
	 * 
	 * @return version of the plugin implementing this command.
	 */
	String getPluginVersion();

	/**
	 * Unique identifier of this command.
	 * 
	 * @return
	 */
	String getCommandId();



}
