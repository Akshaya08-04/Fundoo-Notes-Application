package com.fundoonotes.fundoonotes.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
public class AuthService {

    private final RedisTemplate<String, Object> redisTemplate;

    public AuthService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String register(String email) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("verify:" + email, token, Duration.ofMinutes(30));
        return token;
    }

    public String generateResetOtp(String email) {
        String otp = String.valueOf((int) (100000 + Math.random() * 900000));
        redisTemplate.opsForValue().set("reset:" + email, otp, Duration.ofMinutes(10));
        return otp;
    }

    public void logout(String token) {
        redisTemplate.opsForValue().set("blacklist:" + token, "INVALID", Duration.ofMinutes(60));
    }
}