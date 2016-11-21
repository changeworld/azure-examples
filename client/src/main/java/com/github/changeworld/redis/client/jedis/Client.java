package com.github.changeworld.redis.client.jedis;

import com.github.changeworld.redis.client.RedisClient;
import java.io.IOException;
import redis.clients.jedis.Jedis;

/**
 * @author changeworld
 */
public class Client implements RedisClient {
    private Jedis jedis;

    public Client(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void set(String key, String value) throws IOException {
        try {
            this.jedis.set(key, value);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String get(String key) throws IOException {
        try {
            return this.jedis.get(key);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
