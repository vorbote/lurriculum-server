package cn.vorbote.curriculum;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.vorbote.curriculum.mappers")
@Slf4j
public class CurriculumApplication {

    public static void main(String... args) {
        var ctx = SpringApplication.run(CurriculumApplication.class, args);
        // for (var beanDefinitionName : ctx.getBeanDefinitionNames()) {
        //     System.out.println(beanDefinitionName);
        // }
    }

}
