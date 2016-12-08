package com.github.changeworld.redis.client.lettuce;

import com.github.changeworld.redis.client.SpringDataRedisClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.redis.connection.lettuce.DefaultLettucePool;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author changeworld
 */
public class SpringDataRedisClientTest {
    private static final String FOO = "foo";
    private static final String BAR = "bar";
    private static final String HOST = "localhost";
    private static final int PORT = 6379;
    static final RedisServer REDIS_SERVER = newRedisServer();

    static RedisServer newRedisServer() {
        try {
            return new RedisServer(PORT);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @BeforeClass
    public static void beforeClass() {
        try {
            REDIS_SERVER.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void afterClass() {
        REDIS_SERVER.stop();
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
            assertEquals(BAR, client.get(FOO));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
