package com.example.redismanuallyimplementationdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class UserConfiguration {

    @Bean
    LettuceConnectionFactory getConnection() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);

        return lettuceConnectionFactory;
    }

    @Bean
    RedisTemplate<String,User> getTemplate() {

        RedisTemplate<String,User> redisTemplate = new RedisTemplate<>();

        //for key...
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);


        //for value...
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);

        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);

        // making connections...
        redisTemplate.setConnectionFactory(getConnection());

        return redisTemplate;
    }

    @Bean
    ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }


}
