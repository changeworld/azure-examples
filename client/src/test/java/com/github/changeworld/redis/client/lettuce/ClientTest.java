package com.github.changeworld.redis.client.lettuce;

import com.github.changeworld.redis.client.lettuce.Client;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisStringCommands;
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
    @Test
    public void testSet() throws IOException {
        RedisServer redisServer = new RedisServer(6379);
        Boolean flag = true;
        try {
            redisServer.start();
            RedisClient client = RedisClient.create("redis://localhost");
            Client lettuceClient = new Client(client);
            lettuceClient.set("key1", "value1");
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
            RedisClient client = RedisClient.create("redis://localhost");
            Client lettuceClient = new Client(client);
            lettuceClient.set(key, value);
            assert(lettuceClient.get(key).equals(value));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            redisServer.stop();
        }
    }
}
