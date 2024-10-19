package com.dream.catcher.config;

import com.dream.catcher.dto.SpotPositionDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<Long, SpotPositionDto> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Long, SpotPositionDto> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // JSON 직렬화 설정
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
