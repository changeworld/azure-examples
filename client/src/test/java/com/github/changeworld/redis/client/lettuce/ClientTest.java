package com.github.changeworld.redis.client.lettuce;

import com.lambdaworks.redis.RedisClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author changeworld
 */
public class ClientTest {
    private static Client client;
    private static final String FOO = "foo";
    private static final String BAR = "bar";
    private static final String HOST = "redis://localhost";
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

    @After
    public void after() throws IOException {
        if (client != null) {
            client.close();
        }
    }

    @AfterClass
    public static void afterClass() {
        REDIS_SERVER.stop();
    }

    @Test
    public void shouldLettuceCanGetAfterSet() {
        try {
            client = new Client(RedisClient.create(HOST));
            client.set(FOO, BAR);
            assertEquals(BAR, client.get(FOO));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
