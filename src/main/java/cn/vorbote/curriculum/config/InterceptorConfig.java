package cn.vorbote.curriculum.config;

import cn.vorbote.curriculum.interceptors.UserInterceptor;
import cn.vorbote.simplejwt.AccessKeyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * InterceptorConfig<br>
 * Created at 21/09/2022 13:35
 *
 * @author theod
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final AccessKeyUtil accessKeyUtil;

    private final ObjectMapper objectMapper;

    @Autowired
    public InterceptorConfig(AccessKeyUtil accessKeyUtil,
                             ObjectMapper objectMapper) {
        this.accessKeyUtil = accessKeyUtil;
        this.objectMapper = objectMapper;
    }

    private final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/error",
            "/**/login*",
            "/**/sign-up*",
            "/**/register*",
            "/demo/**/*",
            "/user/my-calendar",
            "/user/verify-phone"
    );

    @Bean
    public UserInterceptor userInterceptor() {
        return new UserInterceptor(accessKeyUtil, objectMapper);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

}
