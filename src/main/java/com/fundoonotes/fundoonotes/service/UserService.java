package com.fundoonotes.fundoonotes.service;

import com.fundoonotes.fundoonotes.dto.request.LoginRequestDto;
import com.fundoonotes.fundoonotes.dto.request.UserRegisterRequestDto;
import com.fundoonotes.fundoonotes.dto.response.LoginResponseDto;
import com.fundoonotes.fundoonotes.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegisterRequestDto requestDto);
    LoginResponseDto login(LoginRequestDto requestDto);
}
