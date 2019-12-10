package com.vbf.instantbuy.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by BRODY on 2019/12/4.
 */

@Service
public class RedisService {

    @Autowired JedisPool jedisPool;

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            key = prefix.getPrefix() + key;
            String str = jedis.get(key);
            return stringToBean(str, clazz);
        } finally {
            returnToPoll(jedis);
        }
    }

    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() == 0) {
                return false;
            }
            key = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if (seconds <= 0) {
                jedis.set(key, str);
            } else {
                jedis.setex(key, seconds, str);
            }
            return true;
        } finally {
            returnToPoll(jedis);
        }
    }

    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            key = prefix.getPrefix() + key;
            return jedis.exists(key);
        } finally {
            returnToPoll(jedis);
        }
    }

    /**
     * Increase 1
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            key = prefix.getPrefix() + key;
            return jedis.incr(key);
        } finally {
            returnToPoll(jedis);
        }
    }

    /**
     * Decrease 1
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            key = prefix.getPrefix() + key;
            return jedis.decr(key);
        } finally {
            returnToPoll(jedis);
        }
    }

    private void returnToPoll(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }

        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }
}
