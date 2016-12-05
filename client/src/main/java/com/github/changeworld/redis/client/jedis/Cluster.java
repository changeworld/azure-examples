package com.github.changeworld.redis.client.jedis;

import com.github.changeworld.redis.client.BaseClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.Set;

/**
 * @author changeworld
 */
public class Cluster implements BaseClient {
    private JedisCluster jedisCluster;

    public Cluster(Set<HostAndPort> jedisClusterNodes){
        this.jedisCluster = new JedisCluster(jedisClusterNodes,
                new GenericObjectPoolConfig());
    }

    public Cluster(Set<HostAndPort> jedisClusterNodes,
                   int connectionTimeout,
                   int soTimeout,
                   int maxAttempts,
                   String password){
        this.jedisCluster = new JedisCluster(jedisClusterNodes,
                connectionTimeout,
                soTimeout,
                maxAttempts,
                password,
                new GenericObjectPoolConfig());
    }

    public JedisCluster getRedisCluster() {
        return this.jedisCluster;
    }

    @Override
    public void set(String key, String value) throws IOException {
        getRedisCluster().set(key, value);
    }

    @Override
    public String get(String key) throws IOException {
        return getRedisCluster().get(key);
    }

    public void close() throws IOException {
        getRedisCluster().close();
    }
}
