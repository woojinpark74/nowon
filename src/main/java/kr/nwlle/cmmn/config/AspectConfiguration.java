package kr.nwlle.cmmn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfiguration {
    @Bean
    SpeedCheckAspect speedCheckAspect() {
        return new SpeedCheckAspect();
    }
}
