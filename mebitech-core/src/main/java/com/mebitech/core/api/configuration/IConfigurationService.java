package com.mebitech.core.api.configuration;

import java.util.Map;

/**
 * 
 * Provides configuration service for all Mebitech core and plugin bundles.
 *
 * 
 */
public interface IConfigurationService {

	//
	// Mebitech configuration
	//

	/**
	 * Debug mode affects Mebitech in various way, if it is enabled, plugin manager
	 * no longer deletes plugins not installed, also Karaf container sets log
	 * level to DEBUG.
	 * 
	 * @return true if debug mode is enabled, false otherwise.
	 */
	Boolean getMebitechDebugEnabled();

	/**
	 * Mebitech create log records for all (LOGIN operation and CRUD operations)
	 * operations in C_OPERATION_LOG table.
	 * 
	 * @return true if we want to log all operations in the system, false
	 *         otherwise.
	 */
	Boolean getMebitechLogOperations();

	//
	// LDAP configuration
	//

	/**
	 * 
	 * @return LDAP server host.
	 */
	String getLdapServer();

	/**
	 * 
	 * @return LDAP server port number.
	 */
	String getLdapPort();

	/**
	 * 
	 * @return LDAP user name.
	 */
	String getLdapUsername();

	/**
	 * 
	 * @return LDAP password.
	 */
	String getLdapPassword();

	/**
	 * 
	 * @return LDAP root DN.
	 */
	String getLdapRootDn();

	/**
	 * 
	 * @return true if using LDAPS, false otherwise.
	 */
	Boolean getLdapUseSsl();

	/**
	 * 
	 * @return true if XMPP client accepts self-signed certificates, false
	 *         otherwise
	 */
	Boolean getLdapAllowSelfSignedCert();

	/**
	 * 
	 * @return LDAP search attributes that can be used in new search editor.
	 */
	String getLdapSearchAttributes();

	

	//
	// IUser configuration
	//

	/**
	 * 
	 * @return LDAP user search base dn for authentication
	 */
	String getUserLdapBaseDn();

	/**
	 * 
	 * @return LDAP user attribute for authentication
	 */
	String getUserLdapUidAttribute();

	/**
	 * 
	 * @return LDAP user privilege attribute.
	 */
	String getUserLdapPrivilegeAttribute();

	/**
	 * 
	 * @return comma separated LDAP user object classes to be used in search
	 *         filter for authentication
	 * 
	 */
	String getUserLdapObjectClasses();

	/**
	 * 
	 * @return true if LDAP authorization enabled, false otherwise
	 */
	Boolean getUserAuthorizationEnabled();

	/**
	 * 
	 * @return comma separated LDAP group object classes (which is usually just
	 *         'groupOfNames')
	 */
	String getGroupLdapObjectClasses();


	//
	// Mail configuration
	//

	/**
	 * 
	 * @return
	 */
	String getMailAddress();

	/**
	 * 
	 * @return
	 */
	String getMailPassword();

	/**
	 * 
	 * @return
	 */
	String getMailHost();

	/**
	 * 
	 * @return
	 */
	Integer getMailSmtpPort();

	/**
	 * 
	 * @return
	 */
	Boolean getMailSmtpAuth();

	/**
	 * 
	 * @return
	 */
	Boolean getMailSmtpStartTlsEnable();

	/**
	 * 
	 * @return
	 */
	Boolean getMailSmtpSslEnable();

	/**
	 * 
	 * @return
	 */
	Integer getMailSmtpConnTimeout();

	/**
	 * 
	 * @return
	 */
	Integer getMailSmtpTimeout();

	/**
	 * 
	 * @return
	 */
	Integer getMailSmtpWriteTimeout();

	/**
	 *
	 * @return
     */
	Map getMenuList();


	/**
	 *
	 * @param id
	 * @return
     */
	Object getMenuById(String id);

	Object getModuleById(String moduleId);

	Object getFormById(String moduleId, String formId);

	Object getPermissionById(String moduleId, String formId, String permissionId);

	String getPermissionPath(String moduleId, String formId, String permissionId);
}
