package com.example.CourseManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank(message =  "Name is Required")
    private String fullName;
    @NotBlank(message = "Emaill is Required")
    @Email(message = "invalid email format")
    private  String email;
    @NotNull(message = "Password is REquired")
    @Size(min = 6,message = "Password must be min 6 Characters")
    private String password;
    @NotBlank(message = "role is required")
    private String role;

}
