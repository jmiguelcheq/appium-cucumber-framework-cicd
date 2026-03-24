package com.cheq.demo_webshop.services;

import com.cheq.demo_webshop.constants.ApiEndpoints;
import com.cheq.demo_webshop.models.request.LoginUserRequest;
import com.cheq.demo_webshop.models.response.AddUserResponse;
import com.cheq.demo_webshop.utils.api.ApiClient;

import io.restassured.response.Response;

public class LoginUserApi {

    private final ApiClient apiClient = new ApiClient();

    public Response loginUser(LoginUserRequest request) {
        return apiClient.post(ApiEndpoints.User.LOGIN_USER, request);
    }

    public AddUserResponse getLoginUserResponse(Response response) {
        return response.as(AddUserResponse.class);
    }
}