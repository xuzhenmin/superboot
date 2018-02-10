package com.zhenmin.superboot.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.Key;
import com.google.common.base.Preconditions;
import com.zhenmin.superboot.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class InterfaceUtil {

    private static final boolean LOGGING_ENABLED = true;
    private static final int NO_RETRY = 0;
    private static final int DEFAULT_TIME_OUT = 10000;
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final Logger logger = LoggerFactory.getLogger(InterfaceUtil.class);
    private static ObjectMapper mapper = JsonUtil.getObjectMapper();
    private static HttpRequestFactory requestFactory =
            HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) {
                    request.setParser(new UrlEncodedParser());
                }
            });


    public static String httpRequest(String url, String data, String method) {
        return httpRequest(url, data, method, DEFAULT_TIME_OUT, NO_RETRY);
    }

    public static String httpRequest(String url, String data, String method, int retryTimes) {
        return httpRequest(url, data, method, DEFAULT_TIME_OUT, retryTimes);
    }

    public static String httpGetRequest(String url) {
        return httpRequest(url, "", HttpMethods.GET, DEFAULT_TIME_OUT, NO_RETRY);
    }


    public static String httpGetRequest(String url, int retryTimes) {
        return httpRequest(url, "", HttpMethods.GET, DEFAULT_TIME_OUT, retryTimes);
    }

    public static String httpGetRequest(String url, int timeoutMilliseconds, int retryTimes) {
        return httpRequest(url, "", HttpMethods.GET, timeoutMilliseconds, retryTimes);
    }


    public static String httpPostRequest(String url, String data) {
        return httpRequest(url, data, HttpMethods.POST, DEFAULT_TIME_OUT, NO_RETRY);
    }

    public static String httpPostRequest(String url, String data, int retryTimes) {
        return httpRequest(url, data, HttpMethods.POST, DEFAULT_TIME_OUT, retryTimes);
    }

    public static String httpPostRequest(String url, String data, int timeoutMilliseconds, int retryTimes) {
        return httpRequest(url, data, HttpMethods.POST, timeoutMilliseconds, retryTimes);
    }


    public static String httpRequest(String url, String data, String method, int timeoutMilliseconds/*毫秒*/, int retryTimes) {
        Preconditions.checkArgument(retryTimes <= 10 && retryTimes >= 0, "retryTimes should between 0(include) and 10(include)");
        method = StringUtils.upperCase(method);
        Preconditions.checkArgument(HttpMethod.resolve(method) != null, "http request method error");
        try {
            HttpRequest request = getHttpRequest(url, data, method);
            long start = System.currentTimeMillis();
            String uuid = StringUtils.left(UUID.randomUUID().toString(), 13);
            logger.info("UUID:{}, request URL:{} , method:{}, data:{}", uuid, url, method, StringUtils.toEncodedString(Base64Utils.decodeFromString(data), Charsets.UTF_8));
            request.setNumberOfRetries(retryTimes);
            request.setConnectTimeout(timeoutMilliseconds);
            request.setLoggingEnabled(LOGGING_ENABLED);
            HttpResponse response = request.execute();
            response.setLoggingEnabled(LOGGING_ENABLED);
            InputStream in = new BufferedInputStream(response.getContent());
            String res = StreamUtils.copyToString(in, Charsets.UTF_8);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      logger.info("UUID:{}, request cost [{}ms], Response data:{}", uuid, (System.currentTimeMillis() - start), res);
            return res;
        } catch (IOException e) {
            logger.warn("Http request error", e);
        }
        return StringUtils.EMPTY;
    }

    private static HttpRequest getHttpRequest(String url, String data, String method) throws IOException {
        if (StringUtils.endsWithIgnoreCase(method, HttpMethods.GET)) {
            return requestFactory.buildGetRequest(new GenericUrl(url));
        }
        RequestContent content = new RequestContent(data);
        UrlEncodedContent urlEncodedContent = new UrlEncodedContent(content);
        return requestFactory.buildRequest(method, new GenericUrl(url), urlEncodedContent);
    }

    /**
     * 判断调用接口返回response是否成功
     *
     * @param response 返回内容
     * @return 是否成功, 判断依据, response中是否有"code":"200" /"code":200
     */
    public static Boolean isResponseSuccess(String response) {
        try {
            JsonNode node = mapper.readTree(response);
            Integer code = node.findPath("header").get("code").asInt();
            return code == 200;
        } catch (Exception e) {
            return false;
        }
    }

    private static class RequestContent {

        @Key("data")
        String data;

        RequestContent(String data) {
            this.data = data;
        }
    }


}
