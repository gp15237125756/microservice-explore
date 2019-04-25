package com.rbsn.schedule.common.support.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisClusterManagerImpl implements JedisManager {

	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String set(String key, String value, int expire) {
		value = jedisCluster.set(key, value);
		if (expire != 0) {
			jedisCluster.expire(key, expire);
		}
		return value;
	}

	@Override
	public long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public long incrBy(String key, long increment) {
		return jedisCluster.incrBy(key,increment);
	}

	@Override
	public long expire(String key, int second) {
		return jedisCluster.expire(key, second);
	}

	@Override
	public long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public long del(String key) {
		
		return jedisCluster.del(key);
	}

	@Override
	public Set<String> getStringKeys(String pattern) {
		return null;
	}

	@Override
	public void flushDB() {

	}

	@Override
	public Long dbSize() {
		return null;
	}

	@Override
	public Long setnx(String key, String value){
		return jedisCluster.setnx(key,value);
	}

	@Override
	public String hget(String hkey, String key) {
		return jedisCluster.hget(hkey, key);
	}

	@Override
	public Map<String,String> hgetAll(String hkey) {
		return jedisCluster.hgetAll(hkey);
	}

	@Override
	public long hset(String hkey, String key, String value) {
		return jedisCluster.hset(hkey, key, value);
	}

	@Override
	public long hdel(String hkey, String key) {
		return jedisCluster.hdel(hkey, key);
	}

	@Override
	public long hlen(String hkey) {
		return jedisCluster.hlen(hkey);
	}

	@Override
	public long hincrBy(String hkey, String key, long increment){
		return jedisCluster.hincrBy(hkey,key,increment);
	}

	@Override
	public List<String> hmget(String hkey, String... keys){
		return jedisCluster.hmget(hkey,keys);
	}

	@Override
	public String hmset(String hkey, Map<String, String> keyValue){
		return jedisCluster.hmset(hkey,keyValue);
	}

	@Override
	public void sadd(String key, String... values) {
		jedisCluster.sadd(key,values);
	}

	@Override
	public Long scard(String key) {
		Long result = Long.valueOf(0);
		result = jedisCluster.scard(key);
		return result;
	}

	@Override
	public Long srem(String key, String... values) {
		Long result = Long.valueOf(0);
		result  = jedisCluster.srem(key, values);
		return result;
	}

	@Override
	public Set<String> smembers(String key) {
		Set<String> values = null;
		values = jedisCluster.smembers(key);
		return values;
	}
}
