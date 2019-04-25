package com.rbsn.schedule.common.support.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JedisManager {

    /**
     * 字符串
     * 获取缓存
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 字符串
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * 字符串
     * 设置缓存
     *
     * @param key
     * @param value
     * @param expire 过去时间
     * @return
     */
    String set(String key, String value, int expire);

    /**
     * 字符串
     * 获取自增值
     *
     * @param key
     * @return
     */
    long incr(String key);

    /**
     * 字符串
     * 将 key 所储存的值加上给定的增量值（increment）
     *
     * @param key
     * @param increment
     * @return
     */
    long incrBy(String key, long increment);

     /**
     * 字符串
     * 设置有效期
     *
     * @param key
     * @param second
     * @return
     */
    long expire(String key, int second);

    /**
     * 字符串
     * 获取有效期
     *
     * @param key
     * @return
     */
    long ttl(String key);

    /**
     * 字符串
     * 删除缓存
     *
     * @param key
     * @return
     */
    long del(String key);

    Set<String> getStringKeys(String pattern);

    /**
     * 刷新数据
     */
    void flushDB();

    Long dbSize();

    /**
     * 字符串
     * key不存在时,创建并设置value 返回1,key存在时,会反回0
     *
     * @param key
     * @param value
     * @return
     */
    public Long setnx(String key, String value);

    /**
     * 哈希
     * 获取缓存
     *
     * @param hkey
     * @param key
     * @return
     */
    String hget(String hkey, String key);

    /**
     * 哈希
     * 获取在哈希表中指定 key 的所有字段和值
     *
     * @param hkey
     * @return
     */
    Map<String,String> hgetAll(String hkey);

    /**
     * 哈希
     * 设置缓存
     *
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    long hset(String hkey, String key, String value);

    /**
     * 哈希
     * 删除缓存
     *
     * @param hkey
     * @param key
     * @return
     */
    long hdel(String hkey, String key);

    /**
     * 哈希
     * 获取哈希表中字段的数量
     *
     * @param hkey
     * @return
     */
    long hlen(String hkey);

    /**
     * 哈希
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param hkey
     * @param key
     * @param increment
     * @return
     */
    long hincrBy(String hkey, String key, long increment);

    /**
     * 哈希
     * 获取所有给定字段的值
     *
     * @param hkey
     * @param keys
     * @return
     */
    List<String> hmget(String hkey, String... keys);

    /**
     * 哈希
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中
     *
     * @param hkey
     * @param keyValue
     * @return
     */
    String hmset(String hkey, Map<String, String> keyValue);


    /**
    * 集合
    * 添加元素
    *
    * @param key
    * @param values
    */
    public void sadd(String key, String... values);

    /**
     * 集合
     * 计算set集合key对应的元素的个数
     *
     * @param key
     * @return
     */
    public Long scard(String key);

    /**
     * 集合
     * 删除key中一个或者多个value
     *
     * @param key
     * @param values
     * @return
     */
    public Long srem(String key, String... values);

    /**
     * 集合
     * 获得key的value集合
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key);
}
