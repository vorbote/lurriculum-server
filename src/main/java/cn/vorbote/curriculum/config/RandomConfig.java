package cn.vorbote.curriculum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * RandomConfig<br>
 * Created at 21/10/2022 10:57
 *
 * @author vorbote
 */
@Configuration
public class RandomConfig {

    @Bean
    public Random randomizer() {
        return new Random();
    }

}
