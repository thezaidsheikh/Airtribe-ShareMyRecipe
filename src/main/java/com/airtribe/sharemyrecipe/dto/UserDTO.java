package com.airtribe.sharemyrecipe.dto;

import com.airtribe.sharemyrecipe.entity.User;

public class UserDTO {
    private String userId;
    private String username;
    private String email;
    private String role;
    private Boolean isEnabled;
    private String token;
    private String refreshToken;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.isEnabled = user.getEnabled();
    }

    // Getters and setters for all fields
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getEnabled() { return isEnabled; }
    public void setEnabled(Boolean enabled) { isEnabled = enabled; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
}
