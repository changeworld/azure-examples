package com.github.changeworld.redis.client.lettuce;

import com.github.changeworld.redis.client.BaseClient;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisStringCommands;

import java.io.IOException;

/**
 * @author changeworld
 */
public class Client implements BaseClient {
    private com.lambdaworks.redis.RedisClient redisClient;

    public Client(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public void set(String key, String value) throws IOException {
        StatefulRedisConnection<String, String> connection = this.redisClient.connect();
        RedisStringCommands sync = connection.sync();
        sync.set(key, value);
    }

    @Override
    public String get(String key) throws IOException {
        StatefulRedisConnection<String, String> connection = this.redisClient.connect();
        RedisStringCommands sync = connection.sync();
        return (String) sync.get(key);
    }
}
