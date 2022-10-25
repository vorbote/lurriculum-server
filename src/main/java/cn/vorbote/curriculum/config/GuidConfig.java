package cn.vorbote.curriculum.config;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.core.utils.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局唯一 ID 配置<br>
 * Created at 2022/9/24 23:47
 *
 * @author vorbote
 */
@Configuration
public class GuidConfig {

    @Bean
    public SnowFlake snowFlake() {
        return new SnowFlake(0, 0);
    }

    @Bean
    public SnowFlake curriculumFlake() {
        var dateTime = new DateTime(2015, 1, 1);
        return new SnowFlake(dateTime.java(), 1, 0);
    }

}
