package com.cheq.demo_webshop.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {

    private RequestSpecBuilderUtil() {
    }

    public static RequestSpecification getBaseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("apiBaseUrl"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getAuthorizedRequestSpec(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("apiBaseUrl"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}