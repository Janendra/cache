package org.janendra.cache;

public interface ICacheService {

	<T> void put(String key, T t);

	<T> void put(String key, T t, int ttlInSeconds);

	<T> T get(String key);

	<T> void delete(String key);

	void close();

}
