package com.example.crawler.service;


import com.example.crawler.model.Contributor;
import com.example.crawler.model.Repository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kpq
 * @since 1.0.0
 */
@Service
@Slf4j
public class GitHubTrendingService {
    public List<Repository> getGitHubTrending() throws IOException {
        List<Repository> repositoryList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://github.com/trending").get();
            Elements articles = doc.getElementsByTag("article");
            articles.forEach(article -> {
                Element head = article.getElementsByClass("h3 lh-condensed").first().getElementsByTag("a").first();
                Repository repository = new Repository();
                setRepositoryInfo(head, repository);
                //中间为描述
                Element description = article.getElementsByClass("col-9 text-gray my-1 pr-4").first();
                if (description != null) {
                    repository.setDescription(description.text());
                }
                Element footer = article.getElementsByClass("f6 text-gray mt-2").first();
                setStarsAndForks(footer, repository);
                repository.setProgrammingLanguage(getProgrammingLanguage(footer));
                repository.setContributors(getContributors(footer));
                repositoryList.add(repository);
            });
        } catch (Exception e) {
            log.error("error{}", e.getMessage());
        }
        return repositoryList;
    }


    private void setStarsAndForks(Element footer, Repository repository) {
        Elements links = footer.getElementsByTag("a");
        repository.setStars(links.get(0).text());
        repository.setForks(links.get(1).text());
    }

    /**
     * @param footer 尾部html 元素
     * @return 编程语言
     */
    private String getProgrammingLanguage(Element footer) {
        Element span = footer.getElementsByClass("d-inline-block ml-0 mr-3").first();
        if (span != null) {
            Element language = footer.getElementsByClass("d-inline-block ml-0 mr-3").first().getElementsByTag("span").last();
            return language.text();
        }
        return "";
    }

    /**
     * 获取仓库作者、项目、项目链接
     *
     * @param head
     * @param repository
     */
    private void setRepositoryInfo(Element head, Repository repository) {
        String[] authorAndTitle = head.attr("href").trim().split("/");
        System.out.println(head.attr("href"));
        repository.setAuthor(authorAndTitle[1]);
        repository.setTitle(authorAndTitle[2]);

        repository.setUrl(head.attr("abs:href"));
    }

    /**
     * 根据html元素解析出每个流行仓库中贡献者集合信息
     * 获取贡献者头像地址和账户链接
     *
     * @param footer 尾部html 元素
     * @return 贡献者集合信息
     */
    private List<Contributor> getContributors(Element footer) {
        List<Contributor> contributorsList = new ArrayList<>();
        Elements links = footer.getElementsByTag("a");
        for (Element link : links) {
            Element element = link.getElementsByClass("avatar mb-1 avatar-user").first();
            if (element != null) {
                String avatar = element.attr("src");
                String accountLink = link.attr("abs:href");
                Contributor contributor = new Contributor(avatar, accountLink);
                contributorsList.add(contributor);
            }
        }
        return contributorsList;
    }

}
