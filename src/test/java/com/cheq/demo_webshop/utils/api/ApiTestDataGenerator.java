package com.cheq.demo_webshop.utils.api;

import java.util.UUID;
import com.cheq.demo_webshop.models.request.AddUserRequest;
import com.cheq.demo_webshop.models.request.LoginUserRequest;

public class ApiTestDataGenerator {

    private ApiTestDataGenerator() {
    }

    public static String generateUniqueEmail(String prefix) {
        String uniqueValue = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return prefix.toLowerCase() + uniqueValue + "@fake.com";
    }

    public static AddUserRequest generateValidAddUserRequest() {
        AddUserRequest request = new AddUserRequest();
        request.setFirstName("Robert");
        request.setLastName("Baratheon");
        request.setEmail(generateUniqueEmail("robert"));
        request.setPassword("robertbaratheon");
        return request;
    }
    
    public static LoginUserRequest generateValidLoginUserRequest(String email, String password) {
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail(email);
        request.setPassword(password);
        return request;
    }
}