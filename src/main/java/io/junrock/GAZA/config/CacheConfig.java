package io.junrock.GAZA.config;
/*
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import springfox.documentation.schema.Entry;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@EnableCaching
@Configuration
public class CacheConfig {
    private final RedisConnectionFactory redisConnectionFactory;

    /**
     CacheProperties : 캐싱이 적용되는 대상마다 캐시의 만료기간을 쉽게 변경할 수 있도록
     yml(또는 properties) 파일에서 종류별로 만료 기간을 Map에 바인딩한다.
     */
/*
    private final CacheProperties cacheProperties;

    private ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        return mapper;
    }

    private RedisCacheConfiguration redisCacheDefaultConfiguration() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper())));
        return redisCacheConfiguration;
    }

    private Map<String, RedisCacheConfiguration> redisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        for (Entry<String, Long> cacheNameAndTimeout : cacheProperties.getTtl().entrySet()) {
            cacheConfigurations
                    .put(cacheNameAndTimeout.getKey(), redisCacheDefaultConfiguration().entryTtl(
                            Duration.ofSeconds(cacheNameAndTimeout.getValue())));
        }
        return cacheConfigurations;
    }

    @Bean
    public CacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheDefaultConfiguration())
                .withInitialCacheConfigurations(redisCacheConfigurationMap()).build();
        return redisCacheManager;
    }
}
*/