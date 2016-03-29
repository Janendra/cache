package org.janendra.cache;

import java.util.concurrent.TimeUnit;

public interface ICacheService {

	<T> void put(String key, T t);

	<T> void put(String key, T t, int units, TimeUnit timeUnit);

	<T> T get(String key);

	<T> void delete(String key);

}
