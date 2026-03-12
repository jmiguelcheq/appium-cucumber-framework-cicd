package com.cheq.demo_webshop.utils;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ApiAllureUtil {

    private ApiAllureUtil() {
    }

    public static void attachText(String title, String content) {
        if (content == null) {
            content = "null";
        }

        Allure.addAttachment(
                title,
                "text/plain",
                new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)),
                ".txt"
        );
    }

    public static void attachJson(String title, String content) {
        if (content == null) {
            content = "{}";
        }

        Allure.addAttachment(
                title,
                "application/json",
                new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)),
                ".json"
        );
    }

    public static void attachRequestDetails(String method, String endpoint, Object requestBody, Map<String, String> headers) {
        attachText("API Method", method);
        attachText("API Endpoint", endpoint);

        if (headers != null && !headers.isEmpty()) {
            attachJson("API Headers", JsonUtil.toJson(headers));
        }

        if (requestBody != null) {
            attachJson("API Request Body", JsonUtil.toJson(requestBody));
        }
    }

    public static void attachResponseDetails(int statusCode, String responseBody) {
        attachText("API Response Status", String.valueOf(statusCode));
        attachJson("API Response Body", responseBody);
    }
}
