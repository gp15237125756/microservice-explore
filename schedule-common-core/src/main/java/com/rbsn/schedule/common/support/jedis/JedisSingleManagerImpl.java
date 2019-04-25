package com.rbsn.schedule.common.support.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisSingleManagerImpl implements JedisManager {

	private static Logger logger = LoggerFactory.getLogger(JedisSingleManagerImpl.class);

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String get(String key) {
		String value = null;
		Jedis jedis = jedisPool.getResource();
		try{
			value = jedis.get(key);
		} catch (Exception e) {
			logger.error("{key={}}",key,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return value;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		try {
			value = jedis.set(key, value);
		}catch (Exception e) {
			logger.error("{key={},value={}}",key,value,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return value;
	}

	@Override
	public String set(String key, String value, int expire) {
		Jedis jedis = jedisPool.getResource();
		try {
			value = jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		}catch (Exception e) {
			logger.error("{key={},value={},expire={}}",key,value,expire,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return value;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result = jedis.incr(key);
		}catch (Exception e) {
			logger.error("{key={}}",key,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public long incrBy(String key, long increment) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result = jedis.incrBy(key,increment);
		}catch (Exception e) {
			logger.error("{key={},increment={}}",key,increment,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result = jedis.expire(key, second);
		} catch (Exception e) {
			logger.error("{key={},second={}}",key,second,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result = jedis.ttl(key);
		} catch (Exception e) {
			logger.error("{key={}}",key,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result = jedis.del(key);
		} catch (Exception e) {
			logger.error("{key={}}",key,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public Set<String> getStringKeys(String pattern) {
		Set<String> keys = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		}catch (Exception e){
			return null;
		}
		try {
			keys = jedis.keys(pattern);
		} catch (Exception e) {
			logger.error("{pattern={}}",pattern,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return keys;
	}

	@Override
	public void flushDB() {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.flushDB();
		} catch (Exception e) {
			logger.error("",e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	@Override
	public Long dbSize() {
		Long dbSize = 0L;
		Jedis jedis = jedisPool.getResource();
		try {
			dbSize = jedis.dbSize();
		} catch (Exception e) {
			logger.error("",e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return dbSize;
	}

	@Override
	public Long setnx(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result= jedis.setnx(key, value);
		} catch (Exception e) {
			logger.error("{key={},value={}}",key,value,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public String hget(String hkey, String key) {
		String value =null;
		Jedis jedis = jedisPool.getResource();
		try{
			value = jedis.hget(hkey, key);
		} catch (Exception e) {
			logger.error("{hkey={},key={}}",hkey,key,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return value;
	}

	@Override
	public Map<String,String> hgetAll(String hkey) {
		Map<String,String> value =null;
		Jedis jedis = jedisPool.getResource();
		try{
			value = jedis.hgetAll(hkey);
		} catch (Exception e) {
			logger.error("{hkey={}}",hkey,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return value;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result= jedis.hset(hkey, key, value);
		} catch (Exception e) {
			logger.error("{hkey={},key={},value={}}",hkey,key,value,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result = jedis.hdel(hkey, key);
		} catch (Exception e) {
			logger.error("{hkey={},key={}}",hkey,key,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public long hlen(String hkey) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result = jedis.hlen(hkey);
		} catch (Exception e) {
			logger.error("{hkey={}}",hkey,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public long hincrBy(String hkey, String key, long increment) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try{
			result = jedis.hincrBy(hkey, key, increment);
		} catch (Exception e) {
			logger.error("{hkey={},key={},increment={}}",hkey,key,increment,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public List<String> hmget(String hkey, String... keys) {
		Jedis jedis = jedisPool.getResource();
		List<String> result = null;
		try{
			result = jedis.hmget(hkey, keys);
		} catch (Exception e) {
			logger.error("{hkey={},keys={}}",hkey,keys);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public String hmset(String hkey, Map<String,String> keyValue) {
		Jedis jedis = jedisPool.getResource();
		String result = null;
		try{
			result = jedis.hmset(hkey, keyValue);
		} catch (Exception e) {
			logger.error("{hkey={},keyValue={}}",hkey,keyValue);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	/**
	 * @description 添加元素
	 * @param key
	 * @param values
	 */
	@Override
	public void sadd(String key, String... values) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.sadd(key,values);
		} catch (Exception e) {
			logger.error("{key={},values={}}",key,values,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	@Override
	public Long scard(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try {
			result = jedis.scard(key);
		} catch (Exception e) {
			logger.error("{key={}}",key,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public Long srem(String key, String... values) {
		Jedis jedis = jedisPool.getResource();
		Long result = Long.valueOf(0);
		try {
			result  = jedis.srem(key, values);
		} catch (Exception e) {
			logger.error("{key={},values={}}",key,values,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = jedisPool.getResource();
		Set<String> values = null;
		try {
			values = jedis.smembers(key);
		} catch (Exception e) {
			logger.error("{key={}}",key,e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return values;
	}
}
