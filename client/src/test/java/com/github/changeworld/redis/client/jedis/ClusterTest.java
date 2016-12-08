package com.github.changeworld.redis.client.jedis;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author changeworld
 */
public class ClusterTest {
    private static Cluster cluster;
    private static Set<HostAndPort> jedisClusterNodes;

    private static final String FOO = "foo";
    private static final String BAR = "bar";
    // In this line, replace <key> with your access key:
    private static final String KEY = "<key>";
    private static final int TIMEOUT = 2000;
    private static final int MAXREDIRECTS = 5;
    private static final int MAXIDLE_AND_TOTAL = 8;
    private static final int MINIDLE = 0;

    @BeforeClass
    public static void beforeClass() throws IOException {
        jedisClusterNodes = new HashSet<HostAndPort>();
        /* In this line, replace <name>, <port> with your cache name, port number:
        jedisClusterNodes.add(new HostAndPort("<name>.redis.cache.windows.net", <port>);
        */
    }

    @After
    public void after() throws IOException {
        if (cluster != null) {
            cluster.close();
        }
    }

    /*
     * Embedded Redis 0.6 supports only the Redis Version 2.8.19.
     * However, Redis 3.0 or greater is required for the Redis Cluster function.
     * So, the following works only with Redis Version 3.x.
    @Test
    public void shouldJedisClusterCanGetAfterSet() {
        try {
            cluster = new Cluster(jedisClusterNodes,
                    TIMEOUT,
                    TIMEOUT,
                    MAXREDIRECTS,
                    KEY);
            cluster.set(FOO, BAR);
            assertTrue(cluster.get(FOO).equals(BAR));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
     */
}
