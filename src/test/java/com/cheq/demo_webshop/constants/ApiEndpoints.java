package com.cheq.demo_webshop.constants;

public final class ApiEndpoints {

    private ApiEndpoints() {}

    public static final class User {
        public static final String USERS = "/users";
        public static final String LOGIN_USER = "/users/login";
        
         // example get user by id
//        public static String getUserById(String userId) {
//            return USERS + "/" + userId;
//        }

        private User() {}
    }
}