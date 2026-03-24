package com.cheq.demo_webshop.utils.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.constants.ApiResponseType;
import com.cheq.demo_webshop.utils.common.LoggerUtil;

import io.restassured.response.Response;

public class ResponseValidator {

    private static final Logger logger = LoggerUtil.getLogger(ResponseValidator.class);

    private ResponseValidator() {
    }

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        assertNotNull("Response should not be null", response);
        assertEquals("Unexpected status code", expectedStatusCode, response.getStatusCode());
    }

    public static void validateResponseType(Response response, ApiResponseType expectedType) {
        assertNotNull("Response should not be null", response);

        ApiResponseType actualType = ResponseHandler.detectResponseType(response);
        assertEquals("Unexpected response type", expectedType, actualType);
    }

    public static void validateJsonFieldValue(Response response, String jsonPath, String expectedValue) {
        assertNotNull("Response should not be null", response);

        ApiResponseType type = ResponseHandler.detectResponseType(response);
        if (type != ApiResponseType.JSON) {
            fail("Expected JSON response but got: " + type + ". Body: " + ResponseHandler.safeBodyAsString(response));
        }

        String actualValue = ResponseHandler.getValueAsString(response, jsonPath);
        assertEquals("Unexpected value for JSON path: " + jsonPath, expectedValue, actualValue);
    }

    public static void validateJsonFieldExists(Response response, String jsonPath) {
        assertNotNull("Response should not be null", response);

        ApiResponseType type = ResponseHandler.detectResponseType(response);
        if (type != ApiResponseType.JSON) {
            fail("Expected JSON response but got: " + type + ". Body: " + ResponseHandler.safeBodyAsString(response));
        }

        Object value = ResponseHandler.getJsonPath(response).get(jsonPath);
        assertNotNull("Expected JSON field to exist: " + jsonPath, value);
    }

    public static void validateBodyContains(Response response, String expectedText) {
        assertNotNull("Response should not be null", response);

        String body = ResponseHandler.safeBodyAsString(response);
        assertTrue("Expected response body to contain: " + expectedText + "\nActual body: " + body,
                body.contains(expectedText));
    }

    public static void validateBodyNotContains(Response response, String unexpectedText) {
        assertNotNull("Response should not be null", response);

        String body = ResponseHandler.safeBodyAsString(response);
        assertFalse("Expected response body not to contain: " + unexpectedText + "\nActual body: " + body,
                body.contains(unexpectedText));
    }

    public static void validateBodyEquals(Response response, String expectedBody) {
        assertNotNull("Response should not be null", response);

        String actualBody = ResponseHandler.safeBodyAsString(response).trim();
        assertEquals("Unexpected response body", expectedBody.trim(), actualBody);
    }

    public static void validateNotEmpty(Response response) {
        assertNotNull("Response should not be null", response);

        String body = ResponseHandler.safeBodyAsString(response);
        assertFalse("Expected response body not to be empty", body.trim().isEmpty());
    }

    public static void validateEmpty(Response response) {
        assertNotNull("Response should not be null", response);

        ApiResponseType type = ResponseHandler.detectResponseType(response);
        assertEquals("Expected empty response body", ApiResponseType.EMPTY, type);
    }

    public static void validateContentTypeContains(Response response, String expectedContentType) {
        assertNotNull("Response should not be null", response);

        String actualContentType = ResponseHandler.safeContentType(response);
        assertTrue("Expected Content-Type to contain: " + expectedContentType + ", but was: " + actualContentType,
                actualContentType.toLowerCase().contains(expectedContentType.toLowerCase()));
    }

    public static void validateXmlContains(Response response, String expectedText) {
    	ApiResponseType type = ResponseHandler.detectResponseType(response);
        if (type != ApiResponseType.XML) {
            fail("Expected XML response but got: " + type + ". Body: " + ResponseHandler.safeBodyAsString(response));
        }

        validateBodyContains(response, expectedText);
    }

    public static void validateHtmlContains(Response response, String expectedText) {
    	ApiResponseType type = ResponseHandler.detectResponseType(response);
        if (type != ApiResponseType.HTML) {
            fail("Expected HTML response but got: " + type + ". Body: " + ResponseHandler.safeBodyAsString(response));
        }

        validateBodyContains(response, expectedText);
    }

    public static void validatePlainTextEquals(Response response, String expectedText) {
    	ApiResponseType type = ResponseHandler.detectResponseType(response);
        if (type != ApiResponseType.PLAIN_TEXT) {
            fail("Expected plain text response but got: " + type + ". Body: " + ResponseHandler.safeBodyAsString(response));
        }

        validateBodyEquals(response, expectedText);
    }

    public static void logAndValidateStatus(Response response, int expectedStatusCode) {
        ResponseHandler.logResponseSummary(response);
        validateStatusCode(response, expectedStatusCode);
        logger.info("Response status validation passed");
    }
}
