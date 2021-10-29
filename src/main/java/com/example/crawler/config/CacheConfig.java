package com.example.crawler.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author kpq
 * @since 1.0.0
 */
@Configuration
public class CacheConfig {


    public class CacheName {

        public static final String TRENDING_HOT = "trending_hot";

        public static final String HOT_DEVELOPS = "hot_develops";

    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Arrays.asList(CacheName.TRENDING_HOT,CacheName.HOT_DEVELOPS));
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.DAYS));
        return cacheManager;
    }

}
