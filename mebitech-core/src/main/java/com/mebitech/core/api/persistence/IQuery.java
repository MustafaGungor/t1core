package com.mebitech.core.api.persistence;

/**
 * Provides standard API for complex database queries
 *
 */
public interface IQuery {
	/**
	 * 
	 * @return query offset
	 */
	int getOffset();

	/**
	 * 
	 * @return max results for query
	 */
	int getMaxResults();

	/**
	 * 
	 * @return array of {@link IQueryCriteria} in this query
	 */
	IQueryCriteria[] getCriteria();
}
