package com.example.pt_online.security;


import com.example.pt_online.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

//UserPrincipal phải implement UserDetails
public class UserPrincipal implements UserDetails {

 private Long id;
 private String email;
 private String password;
 private Collection<? extends GrantedAuthority> authorities;

 // Constructor private để khuyến khích dùng phương thức factory
 private UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
     this.id = id;
     this.email = email;
     this.password = password;
     this.authorities = authorities;
 }

 // Phương thức Factory (Quan trọng): Chuyển đổi User Entity thành UserPrincipal
 public static UserPrincipal create(User user) {
     // Hiện tại, bạn có thể thiết lập quyền (Role) cố định hoặc lấy từ DB (nếu có)
     List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

     return new UserPrincipal(
             user.getId(),
             user.getEmail(),
             user.getPassword(),
             authorities
     );
 }
 
 // Phương thức cần thiết để BE-2 lấy ID người dùng
 public Long getId() {
     return id;
 }

 // --- Các phương thức bắt buộc của UserDetails ---

 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
     return authorities;
 }

 @Override
 public String getPassword() {
     return password;
 }

 @Override
 public String getUsername() {
     return email;
 }
 
 // Giữ mặc định là true
 @Override
 public boolean isAccountNonExpired() { return true; }
 @Override
 public boolean isAccountNonLocked() { return true; }
 @Override
 public boolean isCredentialsNonExpired() { return true; }
 @Override
 public boolean isEnabled() { return true; }
 
 // Dùng để so sánh trong SecurityContextHolder (Quan trọng)
 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     UserPrincipal that = (UserPrincipal) o;
     return Objects.equals(id, that.id);
 }
}