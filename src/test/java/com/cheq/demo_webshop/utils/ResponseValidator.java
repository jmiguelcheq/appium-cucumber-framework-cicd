package com.cheq.demo_webshop.utils;

import io.restassured.response.Response;
import org.junit.Assert;

public class ResponseValidator {

    private ResponseValidator() {
    }

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals("Unexpected status code.",
                expectedStatusCode, response.getStatusCode());
    }

    public static void validateFieldNotNull(Response response, String jsonPath) {
        Object value = response.jsonPath().get(jsonPath);
        Assert.assertNotNull("Expected field to be present: " + jsonPath, value);
    }

    public static void validateFieldEquals(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        Assert.assertEquals("Mismatch for field: " + jsonPath, expectedValue, actualValue);
    }

    public static void validateResponseContains(Response response, String expectedText) {
        String responseBody = response.getBody().asString();
        Assert.assertTrue("Response does not contain expected text: " + expectedText,
                responseBody.contains(expectedText));
    }
}