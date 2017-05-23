package com.mebitech.authorization;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mebitech.core.api.authorization.IAuthService;
import com.mebitech.core.api.configuration.IConfigurationService;
import com.mebitech.core.api.ldap.ILDAPService;
import com.mebitech.core.api.ldap.exceptions.LdapException;
import com.mebitech.core.api.ldap.model.IUser;
import com.mebitech.core.api.ldap.model.LdapEntry;


/**
 * Default implementation of {@link IAuthService}. AuthServiceImpl handles
 * authorization of the requests for specified user. Each user LDAP entry has
 * privilege attributes that defines

 *
 */
public class AuthServiceImpl implements IAuthService {

	private final static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	private static final String ALL_PERMISSION = "ALL";

	private ILDAPService ldapService;

	private IConfigurationService configurationService;

	@Override
	public List<LdapEntry> getPermittedEntries(final String userDn, final List<LdapEntry> targetEntries,
			final String targetOperation) {

		List<LdapEntry> permittedEntries = new ArrayList<LdapEntry>();

		try {
			logger.debug("Authorization started for DN: {} and operation: {}",
					new Object[] { userDn, targetOperation });

			IUser user = ldapService.getUser(userDn);
			if (null == user) {
				logger.warn("Authorization failed. User not found: {}", userDn);
				return null;
			}

			// Find user privileges from LDAP.
			

		} catch (LdapException e) {
			logger.error(e.getMessage(), e);
		}

		return permittedEntries;
	}

	/**
	 * 
	 * @param ldapService
	 */
	public void setLdapService(ILDAPService ldapService) {
		this.ldapService = ldapService;
	}

	/**
	 * 
	 * @param configurationService
	 */
	public void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

}