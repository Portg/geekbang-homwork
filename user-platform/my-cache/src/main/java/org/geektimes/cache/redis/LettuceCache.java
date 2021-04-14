package org.geektimes.cache.redis;

import io.lettuce.core.api.sync.RedisCommands;
import org.geektimes.cache.AbstractCache;
import org.geektimes.cache.ExpirableEntry;
import org.geektimes.cache.codec.SerializedObjectCodec;

import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.configuration.Configuration;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Set;

public class LettuceCache<K extends Serializable, V extends Serializable> extends AbstractCache<K, V> {

    private final RedisCommands redisCommands;

    public LettuceCache(CacheManager cacheManager, String cacheName,
                      Configuration<K, V> configuration, RedisCommands redisCommands) {
        super(cacheManager, cacheName, configuration);
        this.redisCommands = redisCommands;
    }

    @Override
    protected boolean containsEntry(K key) throws CacheException, ClassCastException {
        return redisCommands.exists(key) == 1L;
    }

    @Override
    protected ExpirableEntry<K, V> getEntry(K key) throws CacheException, ClassCastException {
        byte[] keyBytes = new SerializedObjectCodec().encodeKey(key);
        return getEntry(keyBytes);
    }

    protected ExpirableEntry<K, V> getEntry(byte[] keyBytes) throws CacheException, ClassCastException {
        ByteBuffer buffer = ByteBuffer.wrap(keyBytes);
        K key = (K) new SerializedObjectCodec().decodeKey(buffer);
        V value = (V) redisCommands.get(key);
        return ExpirableEntry.of(key, value);
    }

    @Override
    protected void putEntry(ExpirableEntry<K, V> entry) throws CacheException, ClassCastException {
        redisCommands.set(entry.getKey(), entry.getValue());
    }

    @Override
    protected ExpirableEntry<K, V> removeEntry(K key) throws CacheException, ClassCastException {
        ExpirableEntry<K, V> oldEntry = getEntry(key);
        redisCommands.del(key);
        return oldEntry;
    }

    @Override
    protected void clearEntries() throws CacheException {
        // TODO
    }


    @Override
    protected Set<K> keySet() {
        // TODO
        return null;
    }

    @Override
    protected void doClose() {
        this.redisCommands.shutdown(true);
    }

}
