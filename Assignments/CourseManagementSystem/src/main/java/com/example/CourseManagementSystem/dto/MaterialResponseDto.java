package com.example.CourseManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaterialResponseDto {
    private Long id;
    private String title;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private LocalDateTime uploadDate;
}
