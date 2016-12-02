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
    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;

    public Client(RedisClient redisClient) {
        this.redisClient = redisClient;
        this.connection = getRedisClient().connect();
    }

    public RedisClient getRedisClient() {
        return this.redisClient;
    }

    public StatefulRedisConnection<String, String> getConnection() {
        return this.connection;
    }

    @Override
    public void set(String key, String value) throws IOException {
        getConnection().sync().set(key, value);
    }

    @Override
    public String get(String key) throws IOException {
        return (String) getConnection().sync().get(key);
    }

    public void close() {
        getConnection().close();
        getRedisClient().shutdown();
    }
}
