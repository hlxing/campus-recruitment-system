package com.hlx.webserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * @description: HttpSession配置
 * @author: hlx 2018-08-18
 **/
@Configuration
@EnableSpringHttpSession
public class HttpSessionConfig {

    //存储容器连接池
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    //通过头部传递session
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        //自定义头部为S-TOKEN
        return new HeaderHttpSessionIdResolver("S-TOKEN");
    }

}
