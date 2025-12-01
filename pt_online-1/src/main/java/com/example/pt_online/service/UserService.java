package com.example.pt_online.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.pt_online.repository.UserRepository;
import com.example.pt_online.security.JwtService;
import com.example.pt_online.dto.*;
import com.example.pt_online.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.pt_online.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.access.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Xử lý đăng ký
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthResponse("User already exists!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token,"Register success!");
    }

    // Xử lý đăng nhập
    public Long getCurrentUserId() {
        // Logic lấy User ID từ SecurityContextHolder
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (principal instanceof UserPrincipal) {
            return ((UserPrincipal) principal).getId();
        }
        
        // Xử lý trường hợp chưa đăng nhập hoặc principal không đúng
        throw new AccessDeniedException("User not authenticated or invalid principal type."); 
    }

    // Phương thức hỗ trợ JwtAuthenticationFilter
    public UserDetails loadUserById(Integer userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + userId)
        );
        // Giả sử UserPrincipal là lớp của bạn implement UserDetails
        return UserPrincipal.create(user); 
    }
    public AuthResponse login(LoginRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Invalid password!");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token,"Login success!");
    }
}
