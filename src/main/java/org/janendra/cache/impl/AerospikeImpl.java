package org.janendra.cache.impl;

import org.janendra.cache.ICacheService;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;

public class AerospikeImpl implements ICacheService {

	private AerospikeClient client;

	private static final String NAME_SPACE = "demo_namespace";
	private static final String SET_NAME = "cache";
	private static final String BIN_NAME = "";

	public AerospikeImpl() {
		super();
		this.client = new AerospikeClient(getClientPolicy(), getHosts());
	}

	@Override
	public <T> void put(String key, T t) {
		put(key, t, -1);
	}

	@Override
	public <T> void put(String key, T t, int ttlInSeconds) {
		Bin bin = new Bin(BIN_NAME, t);
		WritePolicy writePolicy = new WritePolicy();
		writePolicy.expiration = ttlInSeconds;
		client.put(writePolicy, generateKey(key), bin);
	}

	// TODO:type check
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		Record record = client.get(null, generateKey(key));
		return (T) record.bins.get(BIN_NAME);
	}

	@Override
	public <T> void delete(String key) {
		client.delete(null, generateKey(key));
	}

	@Override
	public void close() {
		client.close();
	}

	private ClientPolicy getClientPolicy() {
		ClientPolicy clientPolicy = new ClientPolicy();
		clientPolicy.failIfNotConnected = true;
		clientPolicy.maxSocketIdle = 100;
		clientPolicy.maxConnsPerNode = 500;
		clientPolicy.timeout = 60000;
		return clientPolicy;
	}

	private Host[] getHosts() {
		String asULRString = System.getProperty("aerospikeHosts");

		if (asULRString == null || asULRString.isEmpty()) {
			throw new RuntimeException("Missing aerospike endpoints");
		}

		String[] endPoints = asULRString.split(",");
		int size = endPoints.length;
		Host[] hosts = new Host[size];
		for (int index = 0; index < size; index++) {
			String[] endPointSplit = endPoints[index].split(":");
			hosts[index] = new Host(endPointSplit[0].trim(), Integer.parseInt(endPointSplit[1].trim()));
		}
		return hosts;
	}

	private Key generateKey(String key) {
		Key key1 = new Key(NAME_SPACE, SET_NAME, key);
		return key1;
	}

}
