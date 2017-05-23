package com.mebitech.core.api.rest.enums;

/**
 * This enum is used to indicate which DN entries to consider when processing a
 * request.
 * 
 *
 */
public enum DNType {

	USER(1), GROUP(2), ALL(3), ORGANIZATIONAL_UNIT(4);

	private int id;

	private DNType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	/**
	 * Provide mapping enums with a fixed ID in JPA (a more robust alternative
	 * to EnumType.String and EnumType.Ordinal)
	 * 
	 * @param id
	 * @return related SessionEvent enum
	 * @see http://blog.chris-ritchie.com/2013/09/mapping-enums-with-fixed-id-in
	 *      -jpa.html
	 * 
	 */
	public static DNType getType(Integer id) {
		if (id == null) {
			return null;
		}
		for (DNType type : DNType.values()) {
			if (id.equals(type.getId())) {
				return type;
			}
		}
		throw new IllegalArgumentException("No matching type for id: " + id);
	}

}
