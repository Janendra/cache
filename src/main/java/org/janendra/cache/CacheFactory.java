package org.janendra.cache;

import org.janendra.cache.enums.CacheType;
import org.janendra.cache.impl.AerospikeImpl;

public class CacheFactory {

	public static ICacheService getCacheService(CacheType cacheType) {
		switch (cacheType) {
		case AEROSPIKE:
			return new AerospikeImpl();
		case CASSANDRA:
			break;
		case COUCHBASE:
			break;
		}
		return null;
	}
}
