package com.example.CourseManagementSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MaterialUploadDto {
    @NotNull(message = "title is required for material ")
    private  String title;
    @NotNull(message = "Courseid is required")
    private  Long courseid;
    @NotNull(message = "file is required")
    private MultipartFile file;

}
