package org.geektimes.cache.codec;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class SerializedObjectCodec<K extends Serializable, V extends Serializable> extends RedisCodec<K, V> {

    private Charset charset = Charset.forName("UTF-8");

    @Override
    public K decodeKey(ByteBuffer bytes) {
        return (K) charset.decode(bytes).toString();
    }

    @Override
    public V decodeValue(ByteBuffer bytes) {
        try {
            byte[] array = new byte[bytes.remaining()];
            bytes.get(array);
            ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(array));
            return (V) is.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public byte[] encodeKey(K key) {
        return charset.encode(String.valueOf(key)).array();
    }

    @Override
    public byte[] encodeValue(V value) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bytes);
            os.writeObject(value);
            return bytes.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
