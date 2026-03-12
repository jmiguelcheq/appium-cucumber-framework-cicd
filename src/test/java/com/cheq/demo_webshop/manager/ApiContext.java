package com.cheq.demo_webshop.manager;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;

public class ApiContext {

    private static final ThreadLocal<Map<String, Object>> context = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {
        context.get().put(key, value);
    }

    public static Object get(String key) {
        return context.get().get(key);
    }

    public static String getString(String key) {
        Object value = context.get().get(key);
        return value != null ? value.toString() : null;
    }

    public static boolean contains(String key) {
        return context.get().containsKey(key);
    }

    public static void remove(String key) {
        context.get().remove(key);
    }

    public static void clear() {
        context.remove();
    }
    
    public static Response getResponse() {
        return (Response) context.get().get("response");
    }
}