package com.fundoonotes.fundoonotes.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.ms}")
    private long expirationMs;

    public String generateToken(Long userId) {
        return "TOKEN_" + userId;
    }

    public Long extractUserId(String token) {
        if (token == null || !token.startsWith("TOKEN_")) {
            throw new RuntimeException("Invalid token");
        }
        return Long.parseLong(token.substring(6));
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("TOKEN_");
    }
}