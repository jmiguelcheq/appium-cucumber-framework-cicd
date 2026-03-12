package com.cheq.demo_webshop.models.response;

public class AddUserResponse {

    private UserResponse user;
    private String token;

    public UserResponse getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}