package com.example.pt_online.dto;

public class AuthResponse {
    private String message;
    private String accessToken;

    public AuthResponse(String message, String accessToken) {
        this.accessToken = accessToken;
        this.message = message;
    }
    public AuthResponse(String message) {
    	this.accessToken = null;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public String getAccessToken() {
    	return accessToken;
    }

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
