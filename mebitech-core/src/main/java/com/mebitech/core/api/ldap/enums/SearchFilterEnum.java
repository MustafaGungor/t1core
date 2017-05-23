package com.mebitech.core.api.ldap.enums;

/**
 * Enum class for filtering attributes.
 * 
 * @see com.mebitech.core.api.ldap.LdapSearchFilterAttribute
 *
 */
public enum SearchFilterEnum {

	EQ("="), NOT_EQ("!=");

	private String operator;

	SearchFilterEnum(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

}
