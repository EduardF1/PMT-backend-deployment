package com.edfis.ppmtool.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/api/users/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET = "JWT_SECRET_KEY";
    public static final String TOKEN_PREFIX = "Bearer "; // the space is important
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 3000000; // in milliseconds (50 minutes)
}
