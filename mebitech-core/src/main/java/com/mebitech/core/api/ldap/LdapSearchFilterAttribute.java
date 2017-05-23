package com.mebitech.core.api.ldap;

import com.mebitech.core.api.ldap.enums.SearchFilterEnum;

/**
 * This class is used to filter LDAP entries during search operations.
 * 
 *
 */
public class LdapSearchFilterAttribute {

	private String attributeName;
	private String attributeValue;
	private SearchFilterEnum operator;

	public LdapSearchFilterAttribute() {
	}

	public LdapSearchFilterAttribute(String attributeName, String attributeValue, SearchFilterEnum operator) {
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.operator = operator;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public SearchFilterEnum getOperator() {
		return operator;
	}

	public void setOperator(SearchFilterEnum operator) {
		this.operator = operator;
	}

}
