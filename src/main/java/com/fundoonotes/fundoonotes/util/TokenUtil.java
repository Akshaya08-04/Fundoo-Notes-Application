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
        if (token == null) {
            throw new RuntimeException("Token is missing");
        }

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (!token.startsWith("TOKEN_")) {
            throw new RuntimeException("Invalid token");
        }

        return Long.parseLong(token.substring(6));
    }

    public boolean validateToken(String token) {
        if (token == null) {
            return false;
        }

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return token.startsWith("TOKEN_");
    }

    public long getTokenExpirySeconds() {
        return expirationMs / 1000;
    }
}