package com.mebitech.core.api.ldap;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * Interface defines ldif file to extend ldap schema
 */
public interface ILdapExtension {
	/**
	 * 
	 * @return ldif file as inputstream
	 * @throws IOException
	 */
	InputStream getLdifFile() throws IOException;
}
