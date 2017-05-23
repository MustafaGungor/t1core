package com.mebitech.config;


import com.mebitech.config.menu.MenuUtil;
import com.mebitech.config.menu.model.Form;
import com.mebitech.config.menu.model.Module;
import com.mebitech.config.menu.model.Permission;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mebitech.core.api.configuration.IConfigurationService;

import java.util.Map;


/**
 * This class provides configurations throughout the system. Configurations are
 * read from <code>${KARAF_HOME}/etc/com.mebitech.cfg</code> file.<br/>
 * 
 * The configuration service is also updated automatically when a configuration
 * property is changed via 'config:edit' or manually.
 * 
 *
 */
public class ConfigurationServiceImpl implements IConfigurationService {

	private static Logger logger = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

	// Mebitech configuration
	private Boolean mebitechDebugEnabled;
	private Boolean mebitechLogOperations;

	// LDAP configuration
	private String ldapServer;
	private String ldapPort;
	private String ldapUsername;
	private String ldapPassword;
	private String ldapRootDn;
	private Boolean ldapUseSsl;
	private String ldapSearchAttributes;
	private Boolean ldapAllowSelfSignedCert;


	// IUser configuration
	private String userLdapBaseDn;
	private String userLdapUidAttribute;
	private String userLdapPrivilegeAttribute;
	private String userLdapObjectClasses;
	private Boolean userAuthorizationEnabled;
	private String groupLdapObjectClasses;


	// Mail configuration
	private String mailAddress;
	private String mailPassword;
	private String mailHost;
	private Integer mailSmtpPort;
	private Boolean mailSmtpAuth;
	private Boolean mailSmtpStartTlsEnable;
	private Boolean mailSmtpSslEnable;
	private Integer mailSmtpConnTimeout;
	private Integer mailSmtpTimeout;
	private Integer mailSmtpWriteTimeout;


	public void refresh() {
		logger.info("Configuration updated using blueprint: {}", prettyPrintConfig());
	}

	@Override
	public String toString() {
		return "ConfigurationServiceImpl [mebitechDebugEnabled=" + mebitechDebugEnabled + ", mebitechLogOperations="
				+ mebitechLogOperations + ", ldapServer=" + ldapServer + ", ldapPort=" + ldapPort + ", ldapUsername="
				+ ldapUsername + ", ldapPassword=" + ldapPassword + ", ldapRootDn=" + ldapRootDn + ", ldapUseSsl="
				+ ldapUseSsl + ", ldapSearchAttributes=" + ldapSearchAttributes + ", ldapAllowSelfSignedCert="
				+ ldapAllowSelfSignedCert + ", userLdapBaseDn=" + userLdapBaseDn
				+ ", userLdapUidAttribute=" + userLdapUidAttribute + ", userLdapPrivilegeAttribute="
				+ userLdapPrivilegeAttribute + ", userLdapObjectClasses=" + userLdapObjectClasses
				+ ", userAuthorizationEnabled=" + userAuthorizationEnabled + ", groupLdapObjectClasses="
				+ groupLdapObjectClasses + ", mailAddress=" + mailAddress + ", mailPassword=" + mailPassword + ", mailHost="
				+ mailHost + ", mailSmtpPort=" + mailSmtpPort + ", mailSmtpAuth=" + mailSmtpAuth
				+ ", mailSmtpStartTlsEnable=" + mailSmtpStartTlsEnable + ", mailSmtpSslEnable=" + mailSmtpSslEnable
				+ ", mailSmtpConnTimeout=" + mailSmtpConnTimeout + ", mailSmtpTimeout=" + mailSmtpTimeout
				+ ", mailSmtpWriteTimeout=" + mailSmtpWriteTimeout + "]";
	}

	public String prettyPrintConfig() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (Exception e) {
		}
		return toString();
	}

	@Override
	public Boolean getMebitechDebugEnabled() {
		return mebitechDebugEnabled;
	}

	public void setMebitechDebugEnabled(Boolean mebitechDebugEnabled) {
		this.mebitechDebugEnabled = mebitechDebugEnabled;
	}

	@Override
	public Boolean getMebitechLogOperations() {
		return mebitechLogOperations;
	}

	public void setMebitechLogOperations(Boolean mebitechLogOperations) {
		this.mebitechLogOperations = mebitechLogOperations;
	}

	@Override
	public String getLdapServer() {
		return ldapServer;
	}

	public void setLdapServer(String ldapServer) {
		this.ldapServer = ldapServer;
	}

	@Override
	public String getLdapPort() {
		return ldapPort;
	}

	public void setLdapPort(String ldapPort) {
		this.ldapPort = ldapPort;
	}

	@Override
	public String getLdapUsername() {
		return ldapUsername;
	}

	public void setLdapUsername(String ldapUsername) {
		this.ldapUsername = ldapUsername;
	}

	@Override
	public String getLdapPassword() {
		return ldapPassword;
	}

	public void setLdapPassword(String ldapPassword) {
		this.ldapPassword = ldapPassword;
	}

	@Override
	public String getLdapRootDn() {
		return ldapRootDn;
	}

	public void setLdapRootDn(String ldapRootDn) {
		this.ldapRootDn = ldapRootDn;
	}

	@Override
	public Boolean getLdapUseSsl() {
		return ldapUseSsl;
	}

	public void setLdapUseSsl(Boolean ldapUseSsl) {
		this.ldapUseSsl = ldapUseSsl;
	}

	@Override
	public Boolean getLdapAllowSelfSignedCert() {
		return ldapAllowSelfSignedCert;
	}

	public void setLdapAllowSelfSignedCert(Boolean ldapAllowSelfSignedCert) {
		this.ldapAllowSelfSignedCert = ldapAllowSelfSignedCert;
	}


	@Override
	public String getUserLdapBaseDn() {
		return userLdapBaseDn;
	}

	public void setUserLdapBaseDn(String userLdapBaseDn) {
		this.userLdapBaseDn = userLdapBaseDn;
	}

	@Override
	public String getUserLdapUidAttribute() {
		return userLdapUidAttribute;
	}

	public void setUserLdapUidAttribute(String userLdapUidAttribute) {
		this.userLdapUidAttribute = userLdapUidAttribute;
	}

	@Override
	public String getUserLdapPrivilegeAttribute() {
		return userLdapPrivilegeAttribute;
	}

	public void setUserLdapPrivilegeAttribute(String userLdapPrivilegeAttribute) {
		this.userLdapPrivilegeAttribute = userLdapPrivilegeAttribute;
	}

	@Override
	public String getUserLdapObjectClasses() {
		return userLdapObjectClasses;
	}

	public void setUserLdapObjectClasses(String userLdapObjectClasses) {
		this.userLdapObjectClasses = userLdapObjectClasses;
	}

	@Override
	public Boolean getUserAuthorizationEnabled() {
		return userAuthorizationEnabled;
	}

	public void setUserAuthorizationEnabled(Boolean userAuthorizationEnabled) {
		this.userAuthorizationEnabled = userAuthorizationEnabled;
	}

	@Override
	public String getGroupLdapObjectClasses() {
		return groupLdapObjectClasses;
	}

	public void setGroupLdapObjectClasses(String groupLdapObjectClasses) {
		this.groupLdapObjectClasses = groupLdapObjectClasses;
	}

	
	@Override
	public String getLdapSearchAttributes() {
		return ldapSearchAttributes;
	}

	public void setLdapSearchAttributes(String ldapSearchAttributes) {
		this.ldapSearchAttributes = ldapSearchAttributes;
	}

	@Override
	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	@Override
	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	@Override
	public Integer getMailSmtpPort() {
		return mailSmtpPort;
	}

	public void setMailSmtpPort(Integer mailSmtpPort) {
		this.mailSmtpPort = mailSmtpPort;
	}

	@Override
	public Boolean getMailSmtpAuth() {
		return mailSmtpAuth;
	}

	public void setMailSmtpAuth(Boolean mailSmtpAuth) {
		this.mailSmtpAuth = mailSmtpAuth;
	}

	@Override
	public Boolean getMailSmtpStartTlsEnable() {
		return mailSmtpStartTlsEnable;
	}

	public void setMailSmtpStartTlsEnable(Boolean mailSmtpStartTlsEnable) {
		this.mailSmtpStartTlsEnable = mailSmtpStartTlsEnable;
	}

	@Override
	public Boolean getMailSmtpSslEnable() {
		return mailSmtpSslEnable;
	}

	public void setMailSmtpSslEnable(Boolean mailSmtpSslEnable) {
		this.mailSmtpSslEnable = mailSmtpSslEnable;
	}

	@Override
	public Integer getMailSmtpConnTimeout() {
		return mailSmtpConnTimeout;
	}

	public void setMailSmtpConnTimeout(Integer mailSmtpConnTimeout) {
		this.mailSmtpConnTimeout = mailSmtpConnTimeout;
	}

	@Override
	public Integer getMailSmtpTimeout() {
		return mailSmtpTimeout;
	}

	public void setMailSmtpTimeout(Integer mailSmtpTimeout) {
		this.mailSmtpTimeout = mailSmtpTimeout;
	}

	@Override
	public Integer getMailSmtpWriteTimeout() {
		return mailSmtpWriteTimeout;
	}

	public void setMailSmtpWriteTimeout(Integer mailSmtpWriteTimeout) {
		this.mailSmtpWriteTimeout = mailSmtpWriteTimeout;
	}


	@Override
	public Map getMenuList() {
		return MenuUtil.getMenuList();
	}

	@Override
	public Object getMenuById(String id) {
		return MenuUtil.getMenuList().get(id);
	}

	public Object getModuleById(String moduleId){
		Module module = (Module)MenuUtil.getMenuList().get(moduleId);
		if(module.getFormList() != null)
			module.getFormList().clear();
		return module;
	}

	public Object getFormById(String moduleId, String formId){
		Module module = (Module)MenuUtil.getMenuList().get(moduleId);
		if(module == null)
			return null;
		Form form = (Form) module.getForms().get(formId);
		if(form == null)
			return null;
		form.getPermissionList().clear();
		return form;
	}

	public String getPermissionPath(String moduleId, String formId, String permissionId){
		Module module = (Module)MenuUtil.getMenuList().get(moduleId);
		if(module == null)
			return null;
		Form form = (Form) module.getForms().get(formId);
		if(form == null)
			return null;
		Permission permission = (Permission) form.getPermissions().get(permissionId);

		return form.getPath() + permission.getPath();
	}


	public Object getPermissionById(String moduleId, String formId, String permissionId){
		Module module = (Module)MenuUtil.getMenuList().get(moduleId);
		if(module == null)
			return null;
		Form form = (Form) module.getForms().get(formId);
		if(form == null)
			return null;
		Permission permission = (Permission) form.getPermissions().get(permissionId);

		return permission;
	}

}
