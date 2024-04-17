package com.eventhub.backend.utils.jwt;

public class JWTConstants {

    /**
     * header key used to retrieve header value from each request
     */
    public static final String HEADER = "Authorization";
    /**
     * prefix attached to every authorization token
     */
    public static final String PREFIX = "Bearer ";

    private JWTConstants() {
    }
}