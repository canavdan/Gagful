package com.gagful.constant;

public class SecurityConstants {
    private SecurityConstants() {
    }

    public static final String KEY_JWT_SECRET = "Emakina";
    public static final long KEY_JWT_EXPIRE_TIME = 423_000_000; // 5 days
    public static final String KEY_JWT_AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";


    public static final String KEY_SECURITY_OPEN_API_PATTERN = "/api/v1/public/**";
    public static final String KEY_SECURITY_SECURE_API_PATTERN = "/api/v1/admin/secure/**";

}

