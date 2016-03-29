package org.janendra.cache.impl;

import java.util.concurrent.TimeUnit;

import org.janendra.cache.ICacheService;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;

public class AerospikeImpl implements ICacheService {

	private AerospikeClient client;

	public AerospikeImpl() {
		super();
		this.client = new AerospikeClient("", 1000);
	}

	@Override
	public <T> void put(String key, T t) {
		put(key, t, -1, TimeUnit.SECONDS);
	}

	@Override
	public <T> void put(String key, T t, int units, TimeUnit timeUnit) {
		Key key1 = new Key("test", "demo", key);
		Bin bin1 = new Bin(null, t);
		WritePolicy p = new WritePolicy();
		// TODO: type cast
		p.expiration = (int) timeUnit.toSeconds(units);
		client.put(null, key1, bin1);
	}

	// TODO:type check
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		Key key1 = new Key("test", "demo", key);
		Record record = client.get(null, key1);
		return (T) record.bins.get(null);
	}

	@Override
	public <T> void delete(String key) {
		Key key1 = new Key("test", "demo", key);
		client.delete(null, key1);
	}

}
