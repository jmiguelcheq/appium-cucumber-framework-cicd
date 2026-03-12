package com.cheq.demo_webshop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static String toJson(Object object) {
        try {
            if (object == null) {
                return "{}";
            }
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            return String.valueOf(object);
        }
    }
}