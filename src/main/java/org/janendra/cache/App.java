package org.janendra.cache;

import org.janendra.cache.enums.CacheType;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		ICacheService a = CacheFactory.getCacheService(CacheType.AEROSPIKE);
		a.put("key2", "BOOO");
		String x = a.get("key2");
		System.out.println(x);
		System.out.println("Hello World!");
		a.close();
	}
}
