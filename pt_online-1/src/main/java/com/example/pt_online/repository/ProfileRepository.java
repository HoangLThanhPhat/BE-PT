// com.example.pt_online.repository.ProfileRepository.java
package com.example.pt_online.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pt_online.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    // Phương thức nghiệp vụ quan trọng: Tìm Profile theo userId
    Optional<Profile> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}