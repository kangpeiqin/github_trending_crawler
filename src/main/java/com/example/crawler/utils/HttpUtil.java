package com.example.crawler.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kpq
 * @since 1.0.0
 */
@Slf4j
public class HttpUtil {

    private static final Map<String, String> REQUEST_HEADERS;

    static {
        REQUEST_HEADERS = new HashMap<>(16);
        REQUEST_HEADERS.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        REQUEST_HEADERS.put("connection", "Keep-Alive");
        REQUEST_HEADERS.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
    }

    /**
     * httpClient发送get请求封装
     *
     * @param uri    请求的url
     * @param params 请求参数
     * @return 抓取的网页
     */
    public static String httpGet(String uri, Map<String, String> params) {
        //获取httpclient客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
            if (null != params) {
                //遍历和封装请求参数
                for (String key : params.keySet()) {
                    uriBuilder.setParameter(key, params.get(key));
                }
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //设置http请求头
            for (Map.Entry<String, String> head : REQUEST_HEADERS.entrySet()) {
                httpGet.addHeader(head.getKey(), head.getValue());
            }
            //执行get请求获取相应
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                resultString = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("关闭响应流出错：{}", e.getMessage());
                }
            }
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    log.error("关闭httpClient流出错：{}", e.getMessage());
                }
            }
        }
        return resultString;
    }
}
