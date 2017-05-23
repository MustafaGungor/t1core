package com.mebitech.core.api.caching;


/**
 * Cache service interface 
 *  
 *
 */
public interface ICacheService {
	/**
	 * 
	 * @param key
	 * @return value
	 */
	Object get(Object key);
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	void put(Object key, Object value);
}
