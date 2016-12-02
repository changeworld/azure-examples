package com.github.changeworld.redis.client;

import java.io.IOException;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author changeworld
 */
public class SpringDataRedisClient implements BaseClient {
    private RedisTemplate<Object, Object> redisTemplate;

    public SpringDataRedisClient(JedisShardInfo jedisShardInfo) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisShardInfo);
        redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
    }

    @Override
    public void set(String key, String value) throws IOException {
        this.redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) throws IOException {
        return (String) this.redisTemplate.opsForValue().get(key);
    }
}
