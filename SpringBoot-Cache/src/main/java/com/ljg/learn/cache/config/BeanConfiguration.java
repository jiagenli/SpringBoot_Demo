package com.ljg.learn.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

@Configuration
public class BeanConfiguration {

    @Bean
    public JdkSerializationRedisSerializer getJdkSerializationRedisSerializer() {
        return new JdkSerializationRedisSerializer();
    }
}
