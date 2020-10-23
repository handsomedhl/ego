package com.ego.redis.dao;

public interface JedisDao {
	boolean exists(String key);
	String set(String key,String value);
	String get(String key);
	long del(String key);
	long expire(String key,int seconds);
}
