package com.cheq.demo_webshop.services;

import com.cheq.demo_webshop.models.request.AddUserRequest;
import com.cheq.demo_webshop.models.response.AddUserResponse;
import com.cheq.demo_webshop.utils.api.ApiClient;
import com.cheq.demo_webshop.constants.ApiEndpoints;

import io.restassured.response.Response;

public class AddUserApi {

    private final ApiClient apiClient = new ApiClient();

    public Response addUser(AddUserRequest request) {
    	return apiClient.post(ApiEndpoints.User.USERS, request);
    }

    public AddUserResponse getAddUserResponse(Response response) {
        return response.as(AddUserResponse.class);
    }
}