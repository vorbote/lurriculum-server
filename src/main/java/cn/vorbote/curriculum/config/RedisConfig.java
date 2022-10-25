package cn.vorbote.curriculum.config;

import cn.vorbote.curriculum.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * RedisConfig<br>
 * Created at 22/10/2022 23:36
 *
 * @author vorbote
 */
@Configuration
public class RedisConfig {

    private final RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    public RedisConfig(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil(redisTemplate);
    }

}
