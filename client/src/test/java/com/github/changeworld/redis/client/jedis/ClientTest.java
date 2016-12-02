package com.github.changeworld.redis.client.jedis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author changeworld
 */
public class ClientTest {
    private static RedisServer redisServer;
    private static Jedis jedis;
    private static Client client;

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
    public void shouldJedisCanSet() throws IOException {
        Boolean flag = true;
        try {
            JedisShardInfo shardInfo = new JedisShardInfo(HOST, PORT);
            jedis = new Jedis(shardInfo);
            client = new Client(jedis);
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
    public void shouldJedisCanGetAfterSet() throws IOException {
        try {
            JedisShardInfo shardInfo = new JedisShardInfo(HOST, PORT);
            jedis = new Jedis(shardInfo);
            client = new Client(jedis);
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
