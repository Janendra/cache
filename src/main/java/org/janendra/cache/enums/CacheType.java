package org.janendra.cache.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CacheType {

	CASSANDRA, COUCHBASE, AEROSPIKE;

	private static final Map<String, CacheType> map = Arrays.stream(values())
			.collect(Collectors.toMap(x -> x.name(), x -> x));

	public static CacheType getEnum(String string) {
		return map.get(string);
	}
}
