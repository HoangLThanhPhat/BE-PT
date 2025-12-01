package com.example.pt_online.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pt_online.dto.AuthResponse;
import com.example.pt_online.dto.LoginRequest;
import com.example.pt_online.dto.RegisterRequest;
import com.example.pt_online.repository.UserRepository;
import com.example.pt_online.security.JwtService;
import com.example.pt_online.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    // Đăng ký tài khoản mới
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }
    // Đăng nhập
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
