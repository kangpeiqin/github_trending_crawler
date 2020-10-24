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
- 提供实时抓取数据接口
### 说明
核心实现逻辑很简单：主要是抓取html-->解析html-->生成api
