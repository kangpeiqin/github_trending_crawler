## Java GitHub Trending Crawler
### 简介
之前看了认识的学长的一篇博文，是关于用PHP抓取GitHub Trending Page并
生成相应的api，感觉写的很不错，详见
[A PHP GitHub Trending Crawler](https://kangzubin.com/github-trending-crawler/)
所以萌生了想用Java语言去实现一下的想法。该项目基于SpringBoot，可以定时抓取并解析
GitHub Trending 数据并缓存，以提供给客户端快速的查询接口。
### 功能一览
- 定时抓取数据进行缓存
- 提供前端预览界面
### 说明
核心实现逻辑很简单：主要是抓取html-->解析html-->生成api
以下是简单的实现(略显粗糙)，但本项目会将代码写得具有扩展性，以便供
其他页面内容爬取解析并生成相应的api。本项目采用`jsoup`对抓取的网页进行解析
附上核心的api生成代码：
```java
public class GithubTrending {
    public static void main(String[] args) throws IOException {
        //最终的返回结果
        List<Map<String, Object>> list = new ArrayList<>();
        //通过http请求获取网页
        Document doc = Jsoup.connect("https://github.com/trending").get();
        //通过jsoup解析html获取所有github trending 条目
        Elements articles = doc.getElementsByTag("article");
        //遍历所有的条目解析网页获取相关字段：难点：如何高效率解析数据
        articles.forEach(article -> {
            Map<String, Object> map = new LinkedHashMap<>();
            List<Map<String, String>> contributors = new ArrayList<>();
            Element head = article.getElementsByClass("h3 lh-condensed").
                    first().getElementsByTag("a").first();
            Element description = article.getElementsByClass("col-9 text-gray my-1 pr-4").first();
            String[] authorAndTitle = head.attr("href").split("/");
            map.put("author", authorAndTitle[1]);
            map.put("title", authorAndTitle[2]);
            map.put("url", head.attr("abs:href"));
            map.put("description", "");
            map.put("programmingLanguage", "");
            if (description != null) {
                map.put("description", description.text());
            }
            Element footer = article.getElementsByClass("f6 text-gray mt-2").first();
            if (footer.getElementsByClass("d-inline-block ml-0 mr-3").first() != null) {
                Element language = footer.getElementsByClass("d-inline-block ml-0 mr-3").first().getElementsByTag("span").last();
                map.put("programmingLanguage", language.text());
            }
            Elements links = footer.getElementsByTag("a");
            map.put("stars", links.get(0).text());
            map.put("forks", links.get(1).text());
            for (Element link : links) {
                if (link.getElementsByClass("avatar mb-1 avatar-user").first() != null) {
                    Map<String, String> contributorMap = new HashMap<>(16);
                    contributorMap.put("avatar", link.getElementsByClass("avatar mb-1 avatar-user").first().attr("src"));
                    contributorMap.put("accountLink", link.attr("abs:href"));
                    contributors.add(contributorMap);
                }
            }
            map.put("contributors", contributors);
            list.add(map);
        });
        System.out.println(list);
    }
}
```