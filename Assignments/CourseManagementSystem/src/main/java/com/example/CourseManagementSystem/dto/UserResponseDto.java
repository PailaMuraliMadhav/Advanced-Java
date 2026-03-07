package com.example.CourseManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {
    private Long id;

    private  String fullName;
    private String email;
    private String role;
    private String profilepic;
    private LocalDateTime createdAt;

}
