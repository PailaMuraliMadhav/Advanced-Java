package com.example.CourseManagementSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDto {

    @NotNull(message = "CourseId is required")
    private Long courseId;

    @NotNull(message = "StudentId is required")
    private Long studentId;
}