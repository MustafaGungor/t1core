package com.mebitech.core.api.ldap.model;

import java.util.Map;

import com.mebitech.core.api.rest.enums.DNType;

/**
 * LDAP entry mapping
 * 

 *
 */
public class LdapEntry {

	/**
	 * distinguished name
	 */
	private String distinguishedName;

	/**
	 * single valued attributes
	 */
	private Map<String, String> attributes;

	private DNType type;

	/**
	 * 
	 * @param dn
	 * @param attributes
	 * @param type
	 */
	public LdapEntry(String dn, Map<String, String> attributes, DNType type) {
		this.distinguishedName = dn;
		this.attributes = attributes;
		this.type = type;
	}

	/**
	 * 
	 * @return
	 */
	public String getDistinguishedName() {
		return distinguishedName;
	}

	/**
	 * 
	 * @return attribute name/value
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * 
	 * @param attribute
	 * @return attribute value
	 */
	public String get(String attribute) {
		return getAttributes().get(attribute);
	}

	/**
	 * 
	 * @return DN type
	 */
	public DNType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "LdapEntry [distinguishedName=" + distinguishedName + ", attributes=" + attributes + ", type=" + type
				+ "]";
	}

}
