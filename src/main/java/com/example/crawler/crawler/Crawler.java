package com.example.crawler.crawler;

import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * @author kpq
 * @since 1.0.0
 */
public interface Crawler {

    /**
     * 通过http get方式采集信息接口
     *
     * @param url           采集信息url
     * @param requestParams 请求参数
     * @return 返回解析后的html
     */
    public Document doFetch(String url, Map<String, String> requestParams);
}
