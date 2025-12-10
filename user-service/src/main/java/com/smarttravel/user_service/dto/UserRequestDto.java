package com.smarttravel.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    public UserRequestDto() {
    }

    public UserRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters & setters

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
