package com.github.changeworld.redis.client.jedis;

import java.io.IOException;

import com.github.changeworld.redis.client.SpringDataRedisClient;
import org.junit.Test;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisShardInfo;
import redis.embedded.RedisServer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author changeworld
 */
public class SpringDataRedisClientTest {
    @Test
    public void testSet() throws IOException {
        RedisServer redisServer = new RedisServer(6379);
        Boolean flag = true;
        try {
            redisServer.start();
            RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
            JedisShardInfo shardInfo = new JedisShardInfo("localhost", 6379);
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(shardInfo);
            redisTemplate.setConnectionFactory(jedisConnectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.afterPropertiesSet();
            SpringDataRedisClient springDataRedisClient = new SpringDataRedisClient(redisTemplate);
            springDataRedisClient.set("key1", "value1");
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            fail();
        } finally {
            redisServer.stop();
        }
        assertTrue(flag);
    }

    @Test
    public void testGet() throws IOException {
        RedisServer redisServer = new RedisServer(6379);
        String key   = "key2";
        String value = "value2";
        try {
            redisServer.start();
            RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
            JedisShardInfo shardInfo = new JedisShardInfo("localhost", 6379);
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(shardInfo);
            redisTemplate.setConnectionFactory(jedisConnectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.afterPropertiesSet();
            SpringDataRedisClient springDataRedisClient = new SpringDataRedisClient(redisTemplate);
            springDataRedisClient.set(key, value);
            assert(springDataRedisClient.get(key).equals(value));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            redisServer.stop();
        }
    }
}
