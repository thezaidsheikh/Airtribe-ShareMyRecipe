package com.airtribe.sharemyrecipe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDTO {
    public interface SignupValidation {}
    public interface LoginValidation {}

    @NotBlank(message = "username is required", groups = {SignupValidation.class, LoginValidation.class})
    private String username;

    @NotBlank(message = "password is required", groups = {SignupValidation.class, LoginValidation.class})
    @Size(min = 6, message = "password must be at least 6 characters long", groups = {SignupValidation.class, LoginValidation.class})
    private String password;

    @NotBlank(message = "email is required", groups = SignupValidation.class)
    @Email(message = "email should be valid", groups = SignupValidation.class)
    private String email;

    // Constructors, getters, and setters
    public AuthDTO() {
    }

    public AuthDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and setters for all fields
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}