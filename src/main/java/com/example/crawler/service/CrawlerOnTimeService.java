package com.example.crawler.service;

import com.example.crawler.constant.UriConstant;
import com.example.crawler.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 定时抓取
 * @author kpq
 * @since 1.0.0
 */
@Slf4j
@Component
public class CrawlerOnTimeService {

    private String cachePrefix = "github_trending";

    @Resource(name = "trendingCacheManager")
    private CacheManager cacheManager;
    @Resource
    GitHubTrendingParser parser;

    @Scheduled(cron = "0 0 10 ? * FRI")
    @Async
    public void fetchOnTime() {
        String html = HttpUtil.httpGet(UriConstant.GITHUB_TRENDING_URI, new HashMap<>(4));
        List list = parser.getTrendingRepositories(html);
        log.info("成功请求到数据：{}", list);
        Cache trendingCache = cacheManager.getCache("trending");
        trendingCache.put(cachePrefix, list);
    }
}
