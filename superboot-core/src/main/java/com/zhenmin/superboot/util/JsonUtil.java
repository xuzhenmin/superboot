package com.zhenmin.superboot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public abstract class JsonUtil {
    private static final ObjectMapper om = new ObjectMapper();
    private static final ObjectMapper prettyMapper = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    static {
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        om.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(String data, TypeReference<T> typeRef) throws IOException {
        return (T) om.readValue(data, typeRef);
    }

    public static String writeValue(Object value) throws IOException {
        return om.writeValueAsString(value);
    }

    public static String writeValueQuite(Object value) {
        String result = StringUtils.EMPTY;
        try {
            result = om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error("write value quite error", e);
        }
        return result;
    }

    public static <T> T parse(String data, Class<T> clazz) throws IOException {
        return om.readValue(data, clazz);
    }

    public static ObjectMapper getObjectMapper() {
        return om;
    }

    public static String pretty(Object obj) {
        try {
            return prettyMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("prettyMapper parse error", e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 将 JsonArray 转换为对应的List
     */
    public static <T> List<T> parseList(String text, Class<T> clazz) throws IOException {
        JavaType type = om.getTypeFactory().constructParametricType(List.class, clazz);
        return om.readValue(text, type);
    }

    /**
     * 转为Map
     */
    public static <K, V> Map<K, V> parseMap(String text, Class<K> keyClass, Class<V> valueClass) throws IOException {
        MapType mapType = om.getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
        return om.readValue(text, mapType);
    }

}
