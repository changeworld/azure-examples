package com.github.changeworld.redis.client.jedis;

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
            JedisShardInfo shardInfo = new JedisShardInfo("localhost", 6379);
            Jedis jedis = new Jedis(shardInfo);
            Client jedisClient = new Client(jedis);
            jedisClient.set("key1", "value1");
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
            JedisShardInfo shardInfo = new JedisShardInfo("localhost", 6379);
            Jedis jedis = new Jedis(shardInfo);
            Client jedisClient = new Client(jedis);
            jedisClient.set(key, value);
            assert(jedisClient.get(key).equals(value));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            redisServer.stop();
        }
    }
}
