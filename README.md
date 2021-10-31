## Java GitHub Trending Crawler
### 简介
相信很多开发者都有逛 GitHub 的习惯，会去看 GitHub Trending，关注每个时段 GitHub 上面的热门项目。
但是我们常常遇到访问速度比较慢的问题，另外，我们在开发第三方 GitHub 应用时，通常也需要展示 Trending 数据，由于 GitHub 官方提供的 API 并不包含 GitHub Trending 相关的接口，如果直接在客户端抓取解析，可能速度会比较慢。
这个仓库提供了一个 Java 小爬虫，用于在服务端抓取解析 GitHub Trending 数据并缓存。
该项目基于 SpringBoot ，以提供给客户端快速的查询接口。可以分别以编程语言（Java、Python、Go、C...）和时间（Daily、Weekly、Monthly）为维度抓取最受关注的 Repositories 和 Developers。
### 说明
- GitHub 网页数据获取：`httpclient`
- HTML 解析: `jsoup`
- 数据缓存：`caffeine cache`
### 响应 Json 数据格式：
> 热门项目：
- 请求接口：`http://localhost:8082/trending/java?since=daily`
- 响应数据：
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
- 请求接口：`http://localhost:8082/hot/develops/java?since=daily`
- 响应数据： 
```
{
    "code":200,
    "msg":"OK",
    "data":[
        {
            "author":"Mattia Iavarone",
            "avatar":"https://avatars.githubusercontent.com/u/15526561?s=96&v=4",
            "accountLink":"https://github.com/natario1",
            "popularRepoName":"CameraView",
            "popularRepoUrl":"https://github.com/natario1/CameraView",
            "popularRepoDescription":"\uD83D\uDCF8 A well documented, high-level Android interface that makes capturing pictures and videos easy, addressing all of the common issues and needs. Real-time filters, gestures, watermarks, frame processing, RAW, output of any size."
        },
        {
            "author":"Nathan Rajlich",
            "avatar":"https://avatars.githubusercontent.com/u/71256?s=96&v=4",
            "accountLink":"https://github.com/TooTallNate",
            "popularRepoName":"Java-WebSocket",
            "popularRepoUrl":"https://github.com/TooTallNate/Java-WebSocket",
            "popularRepoDescription":"A barebones WebSocket client and server implementation written in 100% Java."
        },
     ...
```
### 界面展示
- 热门项目

![GitHub Trending](https://i.bmp.ovh/imgs/2021/10/bdbbcd1d179ee31a.png)
- 热门开发者

![hot developers](https://i.bmp.ovh/imgs/2021/10/a2f058a093c7fe70.png)