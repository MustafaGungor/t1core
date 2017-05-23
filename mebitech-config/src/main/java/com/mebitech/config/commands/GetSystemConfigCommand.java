package com.mebitech.config.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mebitech.core.api.configuration.IConfigurationService;
import com.mebitech.core.api.service.ICommandContext;
import com.mebitech.core.api.service.ICommandResult;
import com.mebitech.core.api.service.ICommandResultFactory;
import com.mebitech.core.api.service.enums.CommandResultStatus;

/**
 * This ICommand implementation provides system configuration (such as LDAP
 * connection parameters and XMPP connection parameters).
 * 
 *
 */
public class GetSystemConfigCommand extends BaseCommand {

	private static Logger logger = LoggerFactory.getLogger(GetSystemConfigCommand.class);

	private ICommandResultFactory resultFactory;
	private IConfigurationService configurationService;

	@Override
	public ICommandResult execute(ICommandContext context) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// LDAP configuration
		resultMap.put("ldapServer", configurationService.getLdapServer());
		resultMap.put("ldapPort", configurationService.getLdapPort());
		resultMap.put("ldapUsername", "TODO"); // TODO
		resultMap.put("ldapPassword", "TODO"); // TODO
		resultMap.put("ldapRootDn", configurationService.getLdapRootDn());
		resultMap.put("ldapUseSsl", configurationService.getLdapUseSsl());

		logger.debug("System config: {}", resultMap);

		return resultFactory.create(CommandResultStatus.OK, new ArrayList<String>(), this, resultMap);
	}

	@Override
	public ICommandResult validate(ICommandContext context) {
		return resultFactory.create(CommandResultStatus.OK, null, this, null);
	}

	@Override
	public String getCommandId() {
		return "GET-SYSTEM-CONFIG";
	}


	public void setResultFactory(ICommandResultFactory resultFactory) {
		this.resultFactory = resultFactory;
	}

	public void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

}
