// com.example.pt_online.service.ProfileService.java
package com.example.pt_online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pt_online.dto.ProfileRequest;
import com.example.pt_online.exception.CustomBadRequestException; // Cần tạo
import com.example.pt_online.exception.ResourceNotFoundException;
import com.example.pt_online.model.Profile;
import com.example.pt_online.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserService userService; // Dùng để lấy ID người dùng hiện tại

    // POST /profile (Tạo mới)
    @Transactional
    public Profile createProfile(ProfileRequest request) {
        Long userId = userService.getCurrentUserId(); // Lấy ID từ Security Context

        if (profileRepository.existsByUserId(userId)) {
            throw new CustomBadRequestException("Profile already exists for this user. Use PUT to update.");
        }

        Profile profile = new Profile();
        profile.setUserId(userId);
        profile.setName(request.getName());
        profile.setHeight(request.getHeight());
        profile.setWeight(request.getWeight());

        return profileRepository.save(profile);
    }

    // PUT /profile (Cập nhật)
    @Transactional
    public Profile updateProfile(ProfileRequest request) {
        Long userId = userService.getCurrentUserId();

        Profile existingProfile = profileRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found for current user."));

        existingProfile.setName(request.getName());
        existingProfile.setHeight(request.getHeight());
        existingProfile.setWeight(request.getWeight());

        return profileRepository.save(existingProfile);
    }

    // GET /profile (Lấy dữ liệu)
    public Profile getProfile() {
        Long userId = userService.getCurrentUserId();

        return profileRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found for current user."));
    }
}