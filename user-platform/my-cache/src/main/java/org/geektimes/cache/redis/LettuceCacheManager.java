package org.geektimes.cache.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.geektimes.cache.AbstractCacheManager;
import org.geektimes.cache.codec.SerializedObjectCodec;

import javax.cache.Cache;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * {@link javax.cache.CacheManager} based on Lettuce
 */
public class LettuceCacheManager extends AbstractCacheManager {

    private final RedisClient redisClient;
    private final StatefulRedisConnection connection;

    public LettuceCacheManager(CachingProvider cachingProvider, URI uri, ClassLoader classLoader, Properties properties) {
        super(cachingProvider, uri, classLoader, properties);
        this.redisClient = RedisClient.create(RedisURI.create((uri)));
        this.connection = redisClient.connect();
    }

    @Override
    protected <K, V, C extends Configuration<K, V>> Cache doCreateCache(String cacheName, C configuration) {
        RedisCommands redisCommands = this.connection.sync();
        return new LettuceCache(this, cacheName, configuration, redisCommands);
    }

    @Override
    protected void doClose() {
        connection.close();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                redisClient.shutdown(0, 10, TimeUnit.MILLISECONDS);
            }
        });
    }
}
