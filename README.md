## Java GitHub Trending Crawler
### 简介
相信很多开发者都有逛 GitHub 的习惯，会去看 GitHub Trending，关注每个时段 GitHub 上面的热门项目。
但是我们常常遇到访问速度比较慢的问题。这个仓库提供了一个 Java 小爬虫，用于在服务端抓取解析 GitHub Trending 数据并缓存。
该项目基于 SpringBoot ，以提供给客户端快速的查询接口。
### 说明
- `HTML` 解析库: `jsoup`
- `HTTP` 请求：`httpclient`
- 相应格式：
> 热门项目：
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
> 热门开发者：
```
[
    {
        "author":"Yair Morgenstern",
        "avatar":"https://avatars.githubusercontent.com/u/8366208?s=96&v=4",
        "accountLink":"https://github.com/yairm210",
        "popularRepoName":"Unciv",
        "popularRepoUrl":"https://github.com/yairm210/Unciv",
        "popularRepoDescription":"Open-source Android/Desktop remake of Civ V"
    },
    {
        "author":"Tim Pope",
        "avatar":"https://avatars.githubusercontent.com/u/378?s=96&v=4",
        "accountLink":"https://github.com/tpope",
        "popularRepoName":"vim-fugitive",
        "popularRepoUrl":"https://github.com/tpope/vim-fugitive",
        "popularRepoDescription":"fugitive.vim: A Git wrapper so awesome, it should be illegal"
    },
    ...
```
### 界面展示
- 热门项目

![GitHub Trending](https://i.bmp.ovh/imgs/2021/10/bdbbcd1d179ee31a.png)
- 热门开发者

![hot developers](https://i.bmp.ovh/imgs/2021/10/a2f058a093c7fe70.png)