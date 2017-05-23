package com.mebitech.persistence;

import com.mebitech.core.api.persistence.IQuery;

/**
 * Default implementation for {@link IQuery}. This class can be used to build
 * complex queries.
 * 
 *
 */
public class QueryImpl implements IQuery {
	
	// TODO use IQuery to build complex queries
	// TODO

	private int offset;
	private int maxResults = 100;
	private QueryCriteriaImpl[] criteria;

	@Override
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	@Override
	public QueryCriteriaImpl[] getCriteria() {
		return criteria;
	}

	public void setCriteria(QueryCriteriaImpl[] criteria) {
		this.criteria = criteria;
	}

}
