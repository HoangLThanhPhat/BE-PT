// com.example.pt_online.dto.ProfileRequest.java
package com.example.pt_online.dto;

// import jakarta.validation.constraints...

public class ProfileRequest {
    // Không cần userId, vì nó được lấy từ Token

    private String name;
    private Double height;
    private Double weight;
    // ... Getters và Setters...
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
}