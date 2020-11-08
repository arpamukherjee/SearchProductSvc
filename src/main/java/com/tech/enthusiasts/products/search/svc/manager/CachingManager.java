package com.tech.enthusiasts.products.search.svc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CachingManager {

	private CacheManager cacheManager;

	@Autowired
	public CachingManager(final CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void clearAllCaches() {
		cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	
	@Scheduled(fixedDelay = 2 * 60 * 60 * 1000)
	public void clearCacheAtSpecificIntervals() {
		clearAllCaches();
	}

}