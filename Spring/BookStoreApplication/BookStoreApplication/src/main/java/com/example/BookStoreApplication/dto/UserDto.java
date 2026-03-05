package com.example.BookStoreApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "Username is Required")
    private String username;
    @NotBlank(message = "Email is required")
    @Email
    private  String email;
    @NotBlank
    @Min(value = 6, message = "Minimum char should be 6")
    private String password;
}
