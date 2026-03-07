package com.example.CourseManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CourseRequestDto {
    @NotBlank(message = "Course title is req")
    private  String title;
    @NotBlank(message = "Course description is required")
    private String description;
    @Positive(message = "price must be positive")
    private  Double price;
    @Positive(message = "duration must be positive")
    private  Integer duration;
    @NotBlank(message = "Course Level is Required")

    private String level;
    private Long instructorId;
}
