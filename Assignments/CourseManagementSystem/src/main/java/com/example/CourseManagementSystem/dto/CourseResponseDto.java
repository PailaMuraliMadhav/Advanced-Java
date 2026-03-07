package com.example.CourseManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseResponseDto {
    private Long id;
    private  String title;
    private  String description;
    private Double price;
    private  Integer duration;
    private String level;
    private Long instructorId;
    private LocalDateTime createdAt;

}
