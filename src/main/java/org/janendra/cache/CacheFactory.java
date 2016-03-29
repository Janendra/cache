package org.janendra.cache;

import org.janendra.cache.enums.CacheType;

public class CacheFactory {

	public static ICacheService getCacheService(CacheType cacheType) {
		switch (cacheType) {
		case AEROSPIKE:
			break;
		case CASSANDRA:
			break;
		case COUCHBASE:
			break;
		}
		return null;
	}
}
