package com.mebitech.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import com.mebitech.core.api.caching.ICacheService;

public class CacheServiceImpl implements ICacheService {

	private CacheManager manager;

	public void init() {

		
		Configuration configuration = new Configuration().defaultCache(new CacheConfiguration("defaultCache", 1000))
				.cache(new CacheConfiguration("mebitech-cache", 1000).timeToIdleSeconds(0).timeToLiveSeconds(0));
		manager = CacheManager.create(configuration);
	}

	public void destroy() {
		manager.shutdown();
	}

	@Override
	public Object get(Object key) {
		if (null == manager.getCache("mebitech-cache").get(key)) {
			return null;
		}
		Element elt = manager.getCache("mebitech-cache").get(key);
		return elt.getObjectValue();
	}

	@Override
	public void put(Object key, Object value) {
		manager.getCache("mebitech-cache").put(new Element(key, value));

	}

}
