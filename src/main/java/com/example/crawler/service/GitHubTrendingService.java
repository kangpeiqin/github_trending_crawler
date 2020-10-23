package com.example.crawler.service;


import com.example.crawler.constant.UriConstant;
import com.example.crawler.model.Repository;
import com.example.crawler.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kpq
 * @since 1.0.0
 */
@Service
@Slf4j
public class GitHubTrendingService {
    @Resource
    GitHubTrendingParser parser;

    public List<Repository> getGitHubTrending(String language, String since) {
        String uri = UriConstant.GITHUB_TRENDING_URI;
        Map<String, String> requestParam = new HashMap<>();
        if (!StringUtils.isEmpty(language)) {
            uri += language;
        }
        if (!StringUtils.isEmpty(since)) {
            requestParam.put("since", since);
        }
        try {
            String html = HttpUtil.httpGet(uri, requestParam);
            List<Repository> repositoryList = parser.getTrendingRepositories(html);
            log.info("成功请求到数据：{}", repositoryList);
            return repositoryList;
        } catch (Exception e) {
            log.error("error{}", e.getMessage());
        }
        return null;
    }
}
