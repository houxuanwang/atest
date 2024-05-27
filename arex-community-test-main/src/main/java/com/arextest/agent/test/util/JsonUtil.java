package com.arextest.agent.test.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daixq
 * @date 2023/02/14
 */
@Slf4j
public class JsonUtil {
    public static String toJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(object);
        } catch (com.fasterxml.jackson.core.JsonProcessingException ex) {
            log.error("toJson JsonProcessingException", ex);
            json = "{\"result\":\"JsonProcessingException\"}";
        }
        return json;
    }

    public static <T> T parseJson(String json, Class<T> targetClass) {
        ObjectMapper mapper = new ObjectMapper();
        T object = null;
        try {
            object = mapper.readValue(json, targetClass);
        } catch (com.fasterxml.jackson.core.JsonProcessingException ex) {
            log.error("parseJson JsonProcessingException", ex);
        }
        return object;
    }
}
