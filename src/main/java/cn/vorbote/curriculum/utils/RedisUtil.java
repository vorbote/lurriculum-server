package cn.vorbote.curriculum.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

/**
 * RedisUtil<br>
 * Created at 22/10/2022 23:31
 *
 * @author vorbote
 */
public final class RedisUtil {

    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisUtil(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> type) {
        return ((T) redisTemplate.opsForValue().get(key));
    }

    @SuppressWarnings("unchecked")
    public <T> T getOrDefault(String key, T defaultValue) {
        return (T) Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse(defaultValue);
    }
}
