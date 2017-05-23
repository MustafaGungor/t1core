package com.mebitech.persistence;

import com.mebitech.core.api.persistence.IQueryCriteria;
import com.mebitech.core.api.persistence.enums.CriteriaOperator;

/**
 * Default implementation for {@link IQueryCriteria}. This class can be used to
 * pass query constraints (criterias) to DAO objects which will then be used to
 * build WHERE statement of a query.
 * 
 *
 */
public class QueryCriteriaImpl implements IQueryCriteria {

	private String field;
	private CriteriaOperator operator;
	private Object[] values = new Object[] {};
	private String type;

	@Override
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public CriteriaOperator getOperator() {
		return operator;
	}

	public void setOperator(CriteriaOperator operator) {
		this.operator = operator;
	}

	@Override
	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
