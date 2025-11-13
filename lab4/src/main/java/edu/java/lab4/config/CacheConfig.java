package edu.java.lab4.config;

import edu.java.lab4.constant.GradingConstants;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(
                GradingConstants.CACHE_COURSES,
                GradingConstants.CACHE_STUDENTS,
                GradingConstants.CACHE_JOURNAL
        );
    }
}