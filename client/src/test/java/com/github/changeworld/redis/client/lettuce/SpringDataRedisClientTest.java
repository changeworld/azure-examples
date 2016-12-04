package com.github.changeworld.redis.client.lettuce;

import com.github.changeworld.redis.client.SpringDataRedisClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.redis.connection.lettuce.DefaultLettucePool;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import redis.embedded.RedisServer;

import java.io.IOException;

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
            DefaultLettucePool defaultLettucePool = new DefaultLettucePool(HOST, PORT);
            defaultLettucePool.afterPropertiesSet();
            LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(defaultLettucePool);
            lettuceConnectionFactory.afterPropertiesSet();
            lettuceConnectionFactory.setShareNativeConnection(true);
            SpringDataRedisClient client = new SpringDataRedisClient(lettuceConnectionFactory);
            client.set(FOO, BAR);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            fail();
        }
        assertTrue(flag);
    }

    @Test
    public void shouldSpringDataRedisCanGetAfterSet() {
        try {
            DefaultLettucePool defaultLettucePool = new DefaultLettucePool(HOST, PORT);
            defaultLettucePool.afterPropertiesSet();
            LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(defaultLettucePool);
            lettuceConnectionFactory.afterPropertiesSet();
            lettuceConnectionFactory.setShareNativeConnection(true);
            SpringDataRedisClient client = new SpringDataRedisClient(lettuceConnectionFactory);
            client.set(FOO, BAR);
            assertTrue(client.get(FOO).equals(BAR));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
