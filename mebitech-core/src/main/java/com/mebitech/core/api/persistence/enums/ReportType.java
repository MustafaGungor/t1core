package com.mebitech.core.api.persistence.enums;

/**
 * Enum class for report types.
 * 
 *
 */
public enum ReportType {

	TABLE(1), PIE_CHART(2), BAR_CHART(3), LINE_CHART(4);

	private int id;

	private ReportType(int id) {
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
	 * @return related ReportType enum
	 * @see http://blog.chris-ritchie.com/2013/09/mapping-enums-with-fixed-id-in
	 *      -jpa.html
	 * 
	 */
	public static ReportType getType(Integer id) {
		if (id == null) {
			return null;
		}
		for (ReportType type : ReportType.values()) {
			if (id.equals(type.getId())) {
				return type;
			}
		}
		throw new IllegalArgumentException("No matching type for id: " + id);
	}

}
