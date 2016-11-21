package com.github.changeworld.redis.client.jedis;

import com.github.changeworld.redis.client.RedisClient;
import java.io.IOException;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author changeworld
 */
public class SpringDataRedisClient implements RedisClient {
    private RedisTemplate<Object, Object> redisTemplate;

    public SpringDataRedisClient(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(String key, String value) throws IOException {
        try {
            this.redisTemplate.opsForValue().set(key, value);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String get(String key) throws IOException {
        try {
            return (String) this.redisTemplate.opsForValue().get(key);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
