package com.github.changeworld.redis.client;

import java.io.IOException;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author changeworld
 */
public class SpringDataRedisClient implements BaseClient {
    private RedisTemplate<Object, Object> redisTemplate;

    public SpringDataRedisClient(RedisConnectionFactory redisConnectionFactory) {
        redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
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
