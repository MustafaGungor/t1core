package com.mebitech.core.api.persistence.enums;

/**
 * CRUD types used in database operations.
 * 

 *
 */
public enum CrudType {
	CREATE(1), READ(2), UPDATE(3), DELETE(4), LOGIN(5);

	private int id;

	private CrudType(int id) {
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
	 * @return related CrudType enum
	 * @see http://blog.chris-ritchie.com/2013/09/mapping-enums-with-fixed-id-in
	 *      -jpa.html
	 * 
	 */
	public static CrudType getType(Integer id) {
		if (id == null) {
			return null;
		}
		for (CrudType crudType : CrudType.values()) {
			if (id.equals(crudType.getId())) {
				return crudType;
			}
		}
		throw new IllegalArgumentException("No matching type for id: " + id);
	}

}
