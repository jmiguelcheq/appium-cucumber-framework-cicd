package com.cheq.demo_webshop.utils.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.utils.common.LoggerUtil;

import java.util.Map;

public class ApiClient {

    private static final Logger logger = LoggerUtil.getLogger(ApiClient.class);

    public Response get(String endpoint) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getBaseRequestSpec());

        logRequest("GET", endpoint, null, null);

        Response response = requestSpec
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        return response;
    }

    public Response get(String endpoint, String token) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token));

        logRequest("GET", endpoint, null, token);

        Response response = requestSpec
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        return response;
    }

    public Response post(String endpoint, Object requestBody) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getBaseRequestSpec())
                .body(requestBody);

        logRequest("POST", endpoint, requestBody, null);

        Response response = requestSpec
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        return response;
    }

    public Response post(String endpoint, Object requestBody, String token) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token))
                .body(requestBody);

        logRequest("POST", endpoint, requestBody, token);

        Response response = requestSpec
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        return response;
    }

    public Response put(String endpoint, Object requestBody, String token) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token))
                .body(requestBody);

        logRequest("PUT", endpoint, requestBody, token);

        Response response = requestSpec
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        return response;
    }

    public Response patch(String endpoint, Object requestBody, String token) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token))
                .body(requestBody);

        logRequest("PATCH", endpoint, requestBody, token);

        Response response = requestSpec
                .when()
                .patch(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        return response;
    }

    public Response delete(String endpoint, String token) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token));

        logRequest("DELETE", endpoint, null, token);

        Response response = requestSpec
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        return response;
    }

    public Response post(String endpoint, Object requestBody, Map<String, String> headers) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getBaseRequestSpec())
                .headers(headers)
                .body(requestBody);

        logRequest("POST", endpoint, requestBody, null);

        Response response = requestSpec
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        return response;
    }

    private void logRequest(String method, String endpoint, Object requestBody, String token) {
        logger.info("API Request Method: {}", method);
        logger.info("API Endpoint: {}", endpoint);

        if (token != null && !token.isBlank()) {
            logger.info("Authorization Token: Bearer {}", token);
        }

        if (requestBody != null) {
            logger.info("Request Body: {}", JsonSerializerUtil.toJson(requestBody));
        }
    }

    private void logResponse(Response response) {
        logger.info("Response Status Code: {}", response.getStatusCode());
        logger.info("Response Body: {}", response.getBody().asPrettyString());
    }
}
