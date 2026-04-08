package com.fundoonotes.fundoonotes.service.impl;

import com.fundoonotes.fundoonotes.service.RedisTokenService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisTokenServiceImpl implements RedisTokenService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTokenServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void blacklistToken(String token, long expirySeconds) {
        redisTemplate.opsForValue().set("blacklist:" + token, "true", Duration.ofSeconds(expirySeconds));
    }

    @Override
    public boolean isBlacklisted(String token) {
        Object value = redisTemplate.opsForValue().get("blacklist:" + token);
        return value != null;
    }

    @Override
    public void storeResetOtp(String email, String otp) {
        redisTemplate.opsForValue().set("reset:" + email, otp, Duration.ofMinutes(10));
    }

    @Override
    public String getResetOtp(String email) {
        Object value = redisTemplate.opsForValue().get("reset:" + email);
        return value == null ? null : value.toString();
    }
}
