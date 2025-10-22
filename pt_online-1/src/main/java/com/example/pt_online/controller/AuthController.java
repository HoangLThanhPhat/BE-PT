package com.example.pt_online.controller;

import com.example.pt_online.dto.*;
import com.example.pt_online.model.User;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Map<String, User> users = new HashMap<>();

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        if (users.containsKey(request.getUsername())) {
            return new AuthResponse("User already exists!");
        }
        users.put(request.getUsername(), new User(request.getUsername(), request.getPassword()));
        return new AuthResponse("Register success!");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        User user = users.get(request.getUsername());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            return new AuthResponse("Login success!");
        }
        return new AuthResponse("Invalid username or password!");
    }
}
