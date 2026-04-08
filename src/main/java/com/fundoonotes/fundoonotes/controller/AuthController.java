package com.fundoonotes.fundoonotes.controller;

import com.fundoonotes.fundoonotes.dto.response.ApiResponse;
import com.fundoonotes.fundoonotes.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestParam String email) {
        String token = authService.register(email);
        return new ApiResponse("User registered. Verification token stored in Redis", token);
    }

    @PostMapping("/reset-otp")
    public ApiResponse resetOtp(@RequestParam String email) {
        String otp = authService.generateResetOtp(email);
        return new ApiResponse("OTP stored in Redis", otp);
    }

    @PostMapping("/logout")
    public ApiResponse logout(@RequestParam String token) {
        authService.logout(token);
        return new ApiResponse("Token blacklisted successfully", null);
    }
}
