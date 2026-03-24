package com.cheq.demo_webshop.utils.api;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializerUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonSerializerUtil() {
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