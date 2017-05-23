package com.mebitech.persistence.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mebitech.core.api.configuration.IConfigurationService;
import com.mebitech.core.api.service.ICommandContext;
import com.mebitech.core.api.service.ICommandResult;
import com.mebitech.core.api.service.ICommandResultFactory;
import com.mebitech.core.api.service.enums.CommandResultStatus;

public class GetLdapSearchAttrCommand extends BaseCommand {

	private static Logger logger = LoggerFactory.getLogger(GetLdapSearchAttrCommand.class);

	private ICommandResultFactory resultFactory;
	private IConfigurationService configurationService;

	@Override
	public ICommandResult execute(ICommandContext context) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// Read LDAP search attributes
		String attributes = configurationService.getLdapSearchAttributes();
		if (attributes != null) {
			resultMap.put("attributes", Arrays.asList(attributes.split(",")));
			logger.debug("LDAP search attributes: {}", attributes);
		}
		// TODO: Read User properties
		Map<String, String> properties = null;
		if (properties != null) {
			resultMap.put("properties", properties);
			logger.debug("properties: {}", properties);
		}
		return resultFactory.create(CommandResultStatus.OK, new ArrayList<String>(), this, resultMap);
	}

	@Override
	public ICommandResult validate(ICommandContext context) {
		return resultFactory.create(CommandResultStatus.OK, null, this, null);
	}

	@Override
	public String getCommandId() {
		return "GET-LDAP-SEARCH-ATTR";
	}

	public void setResultFactory(ICommandResultFactory resultFactory) {
		this.resultFactory = resultFactory;
	}

	public void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}


}
