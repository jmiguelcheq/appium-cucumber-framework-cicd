package com.cheq.demo_webshop.utils.api;

import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.constants.ApiResponseType;
import com.cheq.demo_webshop.utils.common.LoggerUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseHandler {

    private static final Logger logger = LoggerUtil.getLogger(ResponseHandler.class);

    private ResponseHandler() {
    }

    public static ApiResponseType detectResponseType(Response response) {
        if (response == null) {
            throw new IllegalArgumentException("Response cannot be null");
        }

        String body = safeBodyAsString(response);
        String contentType = safeContentType(response).toLowerCase();

        if (body.trim().isEmpty()) {
            return ApiResponseType.EMPTY;
        }

        if (contentType.contains("application/json") || contentType.contains("text/json")) {
            return ApiResponseType.JSON;
        }

        if (contentType.contains("application/xml") || contentType.contains("text/xml")) {
            return ApiResponseType.XML;
        }

        if (contentType.contains("text/html")) {
            return ApiResponseType.HTML;
        }

        if (contentType.contains("text/plain")) {
            return ApiResponseType.PLAIN_TEXT;
        }

        String trimmedBody = body.trim();

        if ((trimmedBody.startsWith("{") && trimmedBody.endsWith("}"))
                || (trimmedBody.startsWith("[") && trimmedBody.endsWith("]"))) {
            return ApiResponseType.JSON;
        }

        if (trimmedBody.startsWith("<?xml") || (trimmedBody.startsWith("<") && trimmedBody.endsWith(">"))) {
            if (trimmedBody.toLowerCase().contains("<html")) {
                return ApiResponseType.HTML;
            }
            return ApiResponseType.XML;
        }

        return ApiResponseType.PLAIN_TEXT;
    }

    public static String safeBodyAsString(Response response) {
        if (response == null || response.getBody() == null) {
            return "";
        }

        try {
            return response.getBody().asString();
        } catch (Exception e) {
            logger.warn("Unable to read response body as string: {}", e.getMessage());
            return "";
        }
    }

    public static String safeContentType(Response response) {
        try {
            return response.getContentType() == null ? "" : response.getContentType();
        } catch (Exception e) {
            logger.warn("Unable to read response content type: {}", e.getMessage());
            return "";
        }
    }

    public static boolean isJson(Response response) {
        return detectResponseType(response) == ApiResponseType.JSON;
    }

    public static boolean isPlainText(Response response) {
        return detectResponseType(response) == ApiResponseType.PLAIN_TEXT;
    }

    public static boolean isXml(Response response) {
        return detectResponseType(response) == ApiResponseType.XML;
    }

    public static boolean isHtml(Response response) {
        return detectResponseType(response) == ApiResponseType.HTML;
    }

    public static boolean isEmpty(Response response) {
        return detectResponseType(response) == ApiResponseType.EMPTY;
    }

    public static JsonPath getJsonPath(Response response) {
    	ApiResponseType type = detectResponseType(response);

        if (type != ApiResponseType.JSON) {
            throw new IllegalStateException(
                    "Response is not JSON. Detected type: " + type + ", body: " + safeBodyAsString(response));
        }

        return response.jsonPath();
    }

    public static String getValueAsString(Response response, String key) {
    	ApiResponseType type = detectResponseType(response);

        switch (type) {
            case JSON:
                Object value = getJsonPath(response).get(key);
                return value == null ? null : String.valueOf(value);

            case PLAIN_TEXT:
            case XML:
            case HTML:
            case EMPTY:
            case UNKNOWN:
            default:
                throw new IllegalStateException(
                        "Cannot extract key [" + key + "] from non-JSON response type: " + type);
        }
    }

    public static void logResponseSummary(Response response) {
    	ApiResponseType type = detectResponseType(response);
        String contentType = safeContentType(response);
        String body = safeBodyAsString(response);

        logger.info("Response Type: {}", type);
        logger.info("Content-Type: {}", contentType);
        logger.info("Status Code: {}", response.getStatusCode());
        logger.info("Response Body: {}", body);
    }
}
