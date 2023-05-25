package com.mustycodified.Reservlyv1be.security;

import com.mustycodified.Reservlyv1be.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 7200000; // 2hrs
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String  HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/v1/users/signup";
    public static final String EMAIL_VERIFICATION_URL = "/api/v1/users/email-verification";
    public static final String PASSWORD_REQUEST_URL = "/api/v1/users/forgot-password";
    public static final String PASSWORD_RESET_URL = "/api/v1/users/reset-password";

    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getSecretKey();
    }

}
