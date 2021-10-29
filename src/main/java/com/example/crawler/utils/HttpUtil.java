package com.example.crawler.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;

/**
 * @author KPQ
 * @date 2021/10/26
 */
@Slf4j
public class HttpUtil {

    private HttpUtil() {
    }

    public static Result get(String url) {
        waitFor();
        return get(url, new JSONObject());
    }

    public static Result get(String url, JSONObject pJson) {
        waitFor();
        //构建请求：请求参数、请求头、请求方法
        HttpUriRequest httpGet = getBaseBuilder(HttpGet.METHOD_NAME)
                .setUri(url)
                .addParameters(getPairList(pJson))
                .build();
        //向服务器发送请求
        return clientExe(httpGet);
    }

    public static NameValuePair[] getPairList(JSONObject pJson) {
        return pJson.entrySet().parallelStream().map(HttpUtil::getNameValuePair).toArray(NameValuePair[]::new);
    }

    private static NameValuePair getNameValuePair(Map.Entry<String, Object> entry) {
        return new BasicNameValuePair(entry.getKey(), StringUtil.get(entry.getValue()));
    }

    public static Result clientExe(HttpUriRequest request) {
        //创建HTTP请求客户端
        Result result = new Result().setStatusCode(500);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            //执行请求
            HttpResponse resp = client.execute(request);
            //获取请求结果
            HttpEntity entity = resp.getEntity();
            String respContent = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            return result.setData(respContent).setStatusCode(resp.getStatusLine().getStatusCode());
        } catch (Exception e) {
            log.info("💔{}请求错误 : ", request.getMethod(), e);
            return result;
        }
    }

    private static RequestBuilder getBaseBuilder(final String method) {
        return RequestBuilder.create(method)
                .addHeader("connection", "keep-alive")
                .addHeader("User-Agent", UserAgent.getOne());
    }

    /**
     * 设置为每次请求预等待 0-3 秒钟
     */
    public static void waitFor() {
        try {
            Thread.sleep(new Random().nextInt(4) * 1000);
        } catch (Exception e) {
            log.warn("等待过程中出错", e);
        }
    }

    private static class UserAgent {
        public static String getOne() {
            int firstNum = new Random().nextInt(30) + 55;
            int thirdNum = new Random().nextInt(3200);
            int fourthNum = new Random().nextInt(140);
            String[] osType = {
                    "(Windows NT 6.1; WOW64)",
                    "(Windows NT 10.0; WOW64)",
                    "(X11; Linux x86_64)",
                    "(Macintosh; Intel Mac OS X 10_12_6)"};
            int index = new Random().nextInt(osType.length);
            String chromeVersion = "Chrome/" + firstNum + ".0." + thirdNum + "." + fourthNum;
            return "Mozilla/5.0 "
                    + osType[index]
                    + " AppleWebKit/537.36"
                    + " (KHTML, like Gecko) "
                    + chromeVersion
                    + " Safari/537.36";
        }
    }

}
