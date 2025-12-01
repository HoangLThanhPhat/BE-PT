// com.example.pt_online.controller.ProfileController.java
package com.example.pt_online.controller;

import com.example.pt_online.dto.ProfileRequest;
import com.example.pt_online.dto.ProfileResponse;
import com.example.pt_online.model.Profile;
import com.example.pt_online.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // 1. POST /api/profile (Tạo mới Profile)
    @PostMapping 
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody ProfileRequest request) {
        // Gọi Service để thực hiện logic nghiệp vụ
        Profile profile = profileService.createProfile(request); 
        
        // Chuyển đổi Entity sang Response DTO trước khi trả về
        return new ResponseEntity<>(convertToResponse(profile), HttpStatus.CREATED); // HTTP 201
    }

    // 2. PUT /api/profile (Cập nhật Profile)
    @PutMapping
    public ProfileResponse updateProfile(@Valid @RequestBody ProfileRequest request) {
        // Gọi Service để thực hiện cập nhật
        Profile profile = profileService.updateProfile(request);
        
        // Chuyển đổi Entity sang Response DTO và trả về (HTTP 200 OK mặc định)
        return convertToResponse(profile); 
    }

    // 3. GET /api/profile (Lấy dữ liệu Profile)
    @GetMapping
    public ProfileResponse getProfile() {
        // Gọi Service để lấy dữ liệu Profile
        Profile profile = profileService.getProfile();
        
        // Chuyển đổi Entity sang Response DTO và trả về (HTTP 200 OK mặc định)
        return convertToResponse(profile); 
    }
    
    // --- Hàm Mapper (Chuyển đổi Entity sang DTO) ---
    /**
     * Chuyển đổi Profile Entity (Model) sang ProfileResponse DTO (Payload).
     * Đây là nơi bạn định hình dữ liệu cuối cùng gửi đến Frontend.
     */
    private ProfileResponse convertToResponse(Profile profile) {
        // Giả sử ProfileResponse có constructor nhận name, height, weight
        return new ProfileResponse(
            profile.getName(), 
            profile.getHeight(), 
            profile.getWeight()
            // Nếu bạn có trường status/BMI, hãy tính toán tại đây nếu cần
        );
    }
}