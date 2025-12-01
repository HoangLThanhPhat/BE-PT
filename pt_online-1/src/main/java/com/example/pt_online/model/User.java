package com.example.pt_online.model;

import jakarta.persistence.Column; // Cần import Column
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 1. SỬA LỖI CÚ PHÁP: Đặt Entity và Table ngay trước khai báo class
@Entity
@Table(name = "user") // Giả sử tên bảng trong DB là "user" (như log CMD bạn gửi)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. SỬA LỖI LOGIC: Đã xóa 'private String password;'
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email; 
    
    // Ánh xạ đến cột 'password_hash' trong DB
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;
    
    // Các trường không có trong DB bạn gửi (cần xóa hoặc thêm vào DB)
    private String username; 
    private String fullName;

    // Constructor rỗng
    public User() {}

    // Constructor đầy đủ (cần sửa lại cho phù hợp với các trường đã chọn)
    public User(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // Getter và Setter
    public Long getId() {
        return id;
    }

    // ... Getters/Setters cho username và fullName (nếu giữ lại)

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Đổi tên Getter/Setter cho mật khẩu để khớp với passwordHash
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}