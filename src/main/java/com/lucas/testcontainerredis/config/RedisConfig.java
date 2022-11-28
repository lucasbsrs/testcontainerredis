package com.lucas.testcontainerredis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
class RedisConfig {

    @Autowired
    public RedisProperties properties;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        var config = new RedisStandaloneConfiguration();
        config.setPassword(properties.getPassword());
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());

        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate redisTemplate() {
        var template = new RedisTemplate<>();
        template.setConnectionFactory((jedisConnectionFactory()));
        return template;
    }

}