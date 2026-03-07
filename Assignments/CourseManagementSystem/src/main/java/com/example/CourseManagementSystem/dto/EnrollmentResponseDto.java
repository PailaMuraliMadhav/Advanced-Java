package com.example.CourseManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollmentResponseDto {
    private  Long id;
    private String courseTitle;
    private  String studentName;
    private  String status;
    private  Integer progressPercentage;
    private LocalDateTime enrollmentDate;

}
