package com.example.pt_online.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.pt_online.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "mysecretkey123456mysecretkey123456"; // ít nhất 32 ký tự

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(User user) {
    	String userIdString = String.valueOf(user.getId());
    	
        return Jwts.builder()
                .setSubject(userIdString)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 ngày
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                // ...
                .parseClaimsJws(token)
                .getBody();

        // Lấy Subject (là User ID) và chuyển đổi ngược lại sang Long
        return Long.parseLong(claims.getSubject()); 
    }
}
