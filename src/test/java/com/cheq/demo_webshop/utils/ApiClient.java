package com.cheq.demo_webshop.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ApiClient {

    private static final Logger logger = LoggerUtil.getLogger(ApiClient.class);

    public Response get(String endpoint) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getBaseRequestSpec());

        logRequest("GET", endpoint, null, null);
        ApiAllureUtil.attachRequestDetails("GET", endpoint, null, null);

        Response response = requestSpec
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        ApiAllureUtil.attachResponseDetails(response.getStatusCode(), response.getBody().asPrettyString());
        return response;
    }

    public Response get(String endpoint, String token) {
    	Map<String, String> headers = Map.of("Authorization", "Bearer " + token);
    	
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token));

        logRequest("GET", endpoint, null, token);
        ApiAllureUtil.attachRequestDetails("GET", endpoint, null, headers);

        Response response = requestSpec
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        ApiAllureUtil.attachResponseDetails(response.getStatusCode(), response.getBody().asPrettyString());
        return response;
    }

    public Response post(String endpoint, Object requestBody) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getBaseRequestSpec())
                .body(requestBody);

        logRequest("POST", endpoint, requestBody, null);
        ApiAllureUtil.attachRequestDetails("POST", endpoint, requestBody, null);

        Response response = requestSpec
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        ApiAllureUtil.attachResponseDetails(response.getStatusCode(), response.getBody().asPrettyString());

        return response;
    }

    public Response post(String endpoint, Object requestBody, String token) {
        Map<String, String> headers = Map.of("Authorization", "Bearer " + token);

        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token))
                .body(requestBody);

        logRequest("POST", endpoint, requestBody, token);
        ApiAllureUtil.attachRequestDetails("POST", endpoint, requestBody, headers);

        Response response = requestSpec
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        ApiAllureUtil.attachResponseDetails(response.getStatusCode(), response.getBody().asPrettyString());

        return response;
    }

    public Response put(String endpoint, Object requestBody, String token) {
    	Map<String, String> headers = Map.of("Authorization", "Bearer " + token);
    	
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token))
                .body(requestBody);

        logRequest("PUT", endpoint, requestBody, token);
        ApiAllureUtil.attachRequestDetails("PUT", endpoint, requestBody, headers);

        Response response = requestSpec
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        ApiAllureUtil.attachResponseDetails(response.getStatusCode(), response.getBody().asPrettyString());
        return response;
    }

    public Response patch(String endpoint, Object requestBody, String token) {
    	Map<String, String> headers = Map.of("Authorization", "Bearer " + token);
    	
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token))
                .body(requestBody);

        logRequest("PATCH", endpoint, requestBody, token);
        ApiAllureUtil.attachRequestDetails("PATCH", endpoint, requestBody, headers);

        Response response = requestSpec
                .when()
                .patch(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        ApiAllureUtil.attachResponseDetails(response.getStatusCode(), response.getBody().asPrettyString());
        return response;
    }

    public Response delete(String endpoint, String token) {
    	Map<String, String> headers = Map.of("Authorization", "Bearer " + token);
    	
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getAuthorizedRequestSpec(token));

        logRequest("DELETE", endpoint, null, token);
        ApiAllureUtil.attachRequestDetails("DELETE", endpoint, null, headers);

        Response response = requestSpec
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        ApiAllureUtil.attachResponseDetails(response.getStatusCode(), response.getBody().asPrettyString());
        return response;
    }

    public Response post(String endpoint, Object requestBody, Map<String, String> headers) {
        RequestSpecification requestSpec = RestAssured.given()
                .spec(RequestSpecBuilderUtil.getBaseRequestSpec())
                .headers(headers)
                .body(requestBody);

        logRequest("POST", endpoint, requestBody, null);
        ApiAllureUtil.attachRequestDetails("POST", endpoint, requestBody, headers);

        Response response = requestSpec
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        logResponse(response);
        ApiAllureUtil.attachResponseDetails(response.getStatusCode(), response.getBody().asPrettyString());
        
        return response;
    }

    private void logRequest(String method, String endpoint, Object requestBody, String token) {
        logger.info("API Request Method: {}", method);
        logger.info("API Endpoint: {}", endpoint);

        if (token != null && !token.isBlank()) {
            logger.info("Authorization Token: Bearer {}", token);
        }

        if (requestBody != null) {
            logger.info("Request Body: {}", requestBody);
        }
    }

    private void logResponse(Response response) {
        logger.info("Response Status Code: {}", response.getStatusCode());
        logger.info("Response Body: {}", response.getBody().asPrettyString());
    }
}