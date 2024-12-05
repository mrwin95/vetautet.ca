package org.vetau.infrastructure.cache.redis;

public interface RedisIfrasService {

    public String getString(String key);
    public void setString(String key, String value);
    public <T> T getObject(String key, Class<T> clazz);
    public void setObject(String key, Object value);
}
