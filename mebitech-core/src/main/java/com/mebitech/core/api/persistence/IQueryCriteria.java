package com.mebitech.core.api.persistence;

import com.mebitech.core.api.persistence.enums.CriteriaOperator;

/**
 * Criteria interface for database queries
 *
 */
public interface IQueryCriteria {

	/**
	 * 
	 * @return operator
	 */
	CriteriaOperator getOperator();

	/**
	 * 
	 * @return field name
	 */
	String getField();

	/**
	 * 
	 * @return values queried for this field
	 */
	Object getValues();

	/**
	 *
	 * @return
	 */
	String getType();
}
