package com.github.changeworld.redis.client;

import java.io.IOException;

/**
 * @author changeworld
 */
public interface BaseClient {
    /**
     * Same as SET command in Redis
     *
     * @param key
     * @param value
     * @throws IOException
     */
    void set(String key, String value) throws IOException;

    /**
     * Same as GET command in Redis
     *
     * @param key
     * @throws IOException
     */
    String get(String key) throws IOException;
}
