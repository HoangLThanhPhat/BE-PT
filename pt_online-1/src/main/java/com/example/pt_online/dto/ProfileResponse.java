// com.example.pt_online.dto.ProfileResponse.java
package com.example.pt_online.dto;

public class ProfileResponse {
    
    // Chỉ trả về những trường mà Frontend cần
    private String name;
    private Double height;
    private Double weight;
    private String status; // Ví dụ: Có thể là trạng thái BMI

    // Constructor để tạo Response từ Profile Entity
    public ProfileResponse(String name, Double height, Double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        // Logic tính toán status (BMI) nếu cần
        this.status = "Healthy"; 
    }

    // Getters và Setters...
}