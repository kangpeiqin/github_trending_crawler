package com.example.crawler.service;

import com.example.crawler.constant.UriConstant;
import com.example.crawler.model.Contributor;
import com.example.crawler.model.Repository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kpq
 * @since 1.0.0
 */
@Component
public class GitHubTrendingParser {

    public List<Repository> getTrendingRepositories(String html) {
        List<Repository> repositoryList = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements articles = doc.getElementsByTag("article");
        articles.forEach(article -> {
            //头部信息：可获取仓库标题、作者、链接
            Element head = article.getElementsByClass("h3 lh-condensed").first().getElementsByTag("a").first();
            Repository repository = new Repository();
            setRepositoryInfo(head, repository);
            //中间为描述
            Element description = article.getElementsByClass("col-9 text-gray my-1 pr-4").first();
            if (description != null) {
                repository.setDescription(description.text());
            }
            //底部为stars、forks、contributors等信息
            Element footer = article.getElementsByClass("f6 text-gray mt-2").first();
            setStarsAndForks(footer, repository);
            repository.setProgrammingLanguage(getProgrammingLanguage(footer));
            repository.setContributors(getContributors(footer));
            repositoryList.add(repository);
        });
        return repositoryList;
    }


    /**
     * 获取仓库作者、项目、项目链接
     *
     * @param head       被解析的html元素
     * @param repository repository
     */
    private void setRepositoryInfo(Element head, Repository repository) {
        String[] authorAndTitle = head.attr("href").trim().split("/");
        repository.setAuthor(authorAndTitle[1]);
        repository.setTitle(authorAndTitle[2]);
        repository.setUrl(UriConstant.GITHUB_URI + head.attr("href"));
    }

    /**
     * 获取仓库的star数和fork数
     *
     * @param footer     被解析的html元素
     * @param repository repository
     */
    private void setStarsAndForks(Element footer, Repository repository) {
        Elements links = footer.getElementsByTag("a");
        repository.setStars(links.get(0).text());
        repository.setForks(links.get(1).text());
    }

    /**
     * @param footer 被解析的html元素
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
                String accountLink = link.attr("href");
                Contributor contributor = new Contributor(avatar, UriConstant.GITHUB_URI + accountLink);
                contributorsList.add(contributor);
            }
        }
        return contributorsList;
    }
}
