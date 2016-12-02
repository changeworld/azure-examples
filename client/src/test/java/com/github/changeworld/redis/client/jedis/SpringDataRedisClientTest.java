package com.github.changeworld.redis.client.jedis;

import java.io.IOException;

import com.github.changeworld.redis.client.SpringDataRedisClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
    private static RedisServer redisServer;

    private static final String FOO = "foo";
    private static final String BAR = "bar";
    private static final String HOST = "localhost";
    private static final int PORT = 6379;

    @BeforeClass
    public static void beforeClass() throws IOException {
        redisServer = new RedisServer(PORT);
        redisServer.start();
    }

    @AfterClass
    public static void afterClass() throws IOException {
        redisServer.stop();
    }

    @Test
    public void shouldSpringDataRedisCanSet() {
        Boolean flag = true;
        try {
            RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
            JedisShardInfo shardInfo = new JedisShardInfo(HOST, PORT);
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(shardInfo);
            redisTemplate.setConnectionFactory(jedisConnectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.afterPropertiesSet();
            SpringDataRedisClient client = new SpringDataRedisClient(redisTemplate);
            client.set(FOO, BAR);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            fail();
        }
        assertTrue(flag);
    }

    @Test
    public void testGet() throws IOException {
        try {
            RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
            JedisShardInfo shardInfo = new JedisShardInfo(HOST, PORT);
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(shardInfo);
            redisTemplate.setConnectionFactory(jedisConnectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.afterPropertiesSet();
            SpringDataRedisClient client = new SpringDataRedisClient(redisTemplate);
            client.set(FOO, BAR);
            assertTrue(client.get(FOO).equals(BAR));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
