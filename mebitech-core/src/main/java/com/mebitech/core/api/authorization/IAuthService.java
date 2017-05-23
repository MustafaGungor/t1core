package com.mebitech.core.api.authorization;

import java.util.List;

import com.mebitech.core.api.ldap.model.LdapEntry;


/**
 * Provides authorization services
 *
 */
public interface IAuthService {

	/**
	 * Calculate 'permitted' LDAP entries of the specified user for the
	 * specified target operation.
	 * 
	 * @param userDn
	 * @param entries
	 * @param targetOperation
	 * @return
	 */
	List<LdapEntry> getPermittedEntries(String userDn, List<LdapEntry> entries, String targetOperation);

}
