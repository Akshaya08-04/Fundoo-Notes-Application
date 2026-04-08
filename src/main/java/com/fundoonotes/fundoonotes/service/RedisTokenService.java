package com.fundoonotes.fundoonotes.service;

public interface RedisTokenService {
    void blacklistToken(String token, long expirySeconds);
    boolean isBlacklisted(String token);
    void storeResetOtp(String email, String otp);
    String getResetOtp(String email);
}
