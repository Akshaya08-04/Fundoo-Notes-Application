package com.fundoonotes.fundoonotes.service.impl;

import com.fundoonotes.fundoonotes.dto.request.LoginRequestDto;
import com.fundoonotes.fundoonotes.dto.request.UserRegisterRequestDto;
import com.fundoonotes.fundoonotes.dto.response.LoginResponseDto;
import com.fundoonotes.fundoonotes.dto.response.UserResponseDto;
import com.fundoonotes.fundoonotes.entity.User;
import com.fundoonotes.fundoonotes.exception.InvalidCredentialsException;
import com.fundoonotes.fundoonotes.exception.UserAlreadyExistsException;
import com.fundoonotes.fundoonotes.exception.UserNotFoundException;
import com.fundoonotes.fundoonotes.repository.UserRepository;
import com.fundoonotes.fundoonotes.service.RedisTokenService;
import com.fundoonotes.fundoonotes.service.UserService;
import com.fundoonotes.fundoonotes.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;
    private final RedisTokenService redisTokenService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           TokenUtil tokenUtil,
                           RedisTokenService redisTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenUtil = tokenUtil;
        this.redisTokenService = redisTokenService;
    }

    @Override
    public UserResponseDto register(UserRegisterRequestDto requestDto) {
        log.info("Register request received for email: {}", requestDto.getEmail());

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );
    }

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        log.info("Login attempt for email: {}", requestDto.getEmail());

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = tokenUtil.generateToken(user.getId());
        return new LoginResponseDto(token, "Login successful");
    }

    @Override
    public String logout(String token) {
        if (!tokenUtil.validateToken(token)) {
            throw new RuntimeException("Invalid token");
        }

        redisTokenService.blacklistToken(token, tokenUtil.getTokenExpirySeconds());
        return "Logout successful";
    }
}