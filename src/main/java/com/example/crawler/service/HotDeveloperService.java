package com.example.crawler.service;

import com.alibaba.fastjson.JSONObject;
import com.example.crawler.config.CacheConfig;
import com.example.crawler.constant.Constant;
import com.example.crawler.model.Developer;
import com.example.crawler.utils.HttpUtil;
import com.example.crawler.utils.Result;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author KPQ
 * @date 2021/10/29
 */
@Service
@Slf4j
public class HotDeveloperService {

    @Cacheable(cacheNames = CacheConfig.CacheName.HOT_DEVELOPS, key = "#language+':'+#since")
    public List<Developer> getDevelopers(String language, String since) {
        List<Developer> list = Lists.newArrayList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("language", language);
        jsonObject.put("since", since);
        Result result = HttpUtil.get(Constant.GITHUB_HOT_DEVELOPERS_URL, jsonObject);
        Document doc = Jsoup.parse((String) result.getData());
        Elements articles = doc.getElementsByClass("Box-row d-flex");
        articles.forEach(article -> {
                    Developer developer = new Developer();
                    Element user = article.getElementsByClass("avatar-user").first();
                    Optional.ofNullable(user).ifPresent(t -> developer.setAvatar(t.attr("src")));
                    Element element = article.getElementsByClass("h3 lh-condensed").first();
                    Optional.ofNullable(element).ifPresent(t -> {
                        Optional<Element> authorInfo = Optional.ofNullable(t.getElementsByTag("a").first());
                        authorInfo.ifPresent(l -> {
                            developer.setAuthor(l.text());
                            developer.setAccountLink(Constant.GITHUB_URL + l.attr("href"));
                        });
                    });
                    Element repoInfo = article.getElementsByClass("h4 lh-condensed").first();
                    Optional.ofNullable(repoInfo).ifPresent(t -> {
                        Element repo = t.getElementsByTag("a").first();
                        developer.setPopularRepoName(repo.text());
                        developer.setPopularRepoUrl(Constant.GITHUB_URL + repo.attr("href"));
                    });
                    Element desc = article.getElementsByClass("f6 color-text-secondary mt-1").first();
                    Optional.ofNullable(desc).ifPresent(t -> developer.setPopularRepoDescription(t.text()));
                    list.add(developer);
                }
        );
        return list;
    }

}
