## Java GitHub Trending Crawler
### 简介
之前看了认识的学长的一篇博文，是关于用PHP抓取GitHub Trending Page并
生成相应的api，感觉写的很不错，详见
[A PHP GitHub Trending Crawler](https://kangzubin.com/github-trending-crawler/)
所以萌生了想用Java语言去实现一下的想法。该项目基于SpringBoot，可以定时抓取并解析
GitHub Trending 数据并缓存，以提供给客户端快速的查询接口。
### 功能一览
- 定时抓取数据进行缓存,提供秒级响应
- 提供前端预览界面
- 提供实时抓取数据接口,和缓存数据接口
### 说明
相应格式：
```
{
    "code": 200,
    "msg": "OK",
    "data": [
        {
            "title": "jdk",
            "author": "openjdk",
            "url": "https://www.github.com/openjdk/jdk",
            "programmingLanguage": "Java",
            "description": "JDK main-line development",
            "stars": "6,231",
            "forks": "1,402",
            "contributors": [
                {
                    "avatar": "https://avatars3.githubusercontent.com/u/61436102?s=40&v=4",
                    "accountLink": "https://www.github.com/jonathan-gibbons"
                },
                {
                    "avatar": "https://avatars0.githubusercontent.com/u/31372256?s=40&v=4",
                    "accountLink": "https://www.github.com/jddarcy"
                },
                {
                    "avatar": "https://avatars2.githubusercontent.com/u/62058229?s=40&v=4",
                    "accountLink": "https://www.github.com/ChrisHegarty"
                },
                {
                    "avatar": "https://avatars0.githubusercontent.com/u/39598758?s=40&v=4",
                    "accountLink": "https://www.github.com/JesperIRL"
                },
                {
                    "avatar": "https://avatars1.githubusercontent.com/u/574013?s=40&v=4",
                    "accountLink": "https://www.github.com/wangweij"
                }
            ]
        },
  ...
```
