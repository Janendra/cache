package org.janendra.cache.factory;

import org.janendra.cache.ICacheService;
import org.janendra.cache.enums.CacheType;
import org.janendra.cache.impl.AerospikeImpl;

public class CacheFactory {

	private static final ICacheService aerospikeService = new AerospikeImpl();

	public static ICacheService getCacheService(CacheType cacheType) {
		switch (cacheType) {
		case AEROSPIKE:
			return aerospikeService;
		case CASSANDRA:
			break;
		case COUCHBASE:
			break;
		}
		return null;
	}
}
