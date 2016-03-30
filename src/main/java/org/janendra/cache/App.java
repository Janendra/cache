package org.janendra.cache;

import org.janendra.cache.enums.CacheType;
import org.janendra.cache.model.Customer;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		String cacheType = System.getProperty("cacheType");
		if (CacheType.getEnum(cacheType) == null) {
			throw new RuntimeException("Invalid cachetype");
		}

		ICacheService a = CacheFactory.getCacheService(CacheType.getEnum(cacheType));
		a.put("key2", new Customer("a", "b", "c"));

		Customer x = a.get("key2");
		System.out.println(x);

		a.delete("key2");

		x = a.get("key2");
		System.out.println(x);

		System.out.println("Hello World!");
		a.close();
	}
}
