package com.example.CourseManagementSystem.Controllers;

import com.example.CourseManagementSystem.Service.CourseMaterialService;
import com.example.CourseManagementSystem.dto.MaterialResponseDto;
import com.example.CourseManagementSystem.dto.MaterialUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/materials")
public class CourseMaterialController {
    private final CourseMaterialService materialService;

    @PostMapping("/upload")
    public ResponseEntity<MaterialResponseDto> uploadMaterial(
            @ModelAttribute MaterialUploadDto request){

        return ResponseEntity.ok(materialService.uploadMaterial(request));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<MaterialResponseDto>> getMaterialsByCourse(
            @PathVariable Long courseId){

        return ResponseEntity.ok(materialService.getMaterialsByCourse(courseId));
    }
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadMaterial(@PathVariable Long id){

        return materialService.downloadMaterial(id);
    }
}
