package cn.vorbote.curriculum.config;

import cn.vorbote.curriculum.enums.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * SmsSenderConfig<br>
 * Created at 2022/10/11 17:06
 *
 * @author vorbote
 */
@Configuration
public class SmsSenderConfig {

    @Bean
    public List<Region> blockedRegions() {
        return List.of(
                Region.LITHUANIA,
                Region.SOUTH_KOREA
        );
    }

}
