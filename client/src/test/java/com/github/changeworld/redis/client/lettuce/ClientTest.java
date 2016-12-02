package com.github.changeworld.redis.client.lettuce;

import com.lambdaworks.redis.RedisClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author changeworld
 */
public class ClientTest {
    private static RedisServer redisServer;
    private static RedisClient lettuce;
    private static Client client;

    private static final String FOO = "foo";
    private static final String BAR = "bar";
    private static final String HOST = "redis://localhost";
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
    public void shouldLettuceCanSet() {
        Boolean flag = true;
        try {
            lettuce = RedisClient.create(HOST);
            client = new Client(lettuce);
            client.set(FOO, BAR);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            fail();
        } finally {
            client.close();
        }
        assertTrue(flag);
    }

    @Test
    public void shouldLettuceCanGetAfterSet() {
        try {
            lettuce = RedisClient.create(HOST);
            client = new Client(lettuce);
            client.set(FOO, BAR);
            assertTrue(client.get(FOO).equals(BAR));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            client.close();
        }
    }
}
