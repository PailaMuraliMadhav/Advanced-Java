package com.example.CourseManagementSystem.Controllers;

import com.example.CourseManagementSystem.Service.EnrollmentService;
import com.example.CourseManagementSystem.dto.EnrollmentRequestDto;
import com.example.CourseManagementSystem.dto.EnrollmentResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> enrollStudent(
            @Valid @RequestBody EnrollmentRequestDto request){

        return ResponseEntity.ok(enrollmentService.enrollStudent(request));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollmentsByStudent(
            @PathVariable Long studentId){

        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollmentsByCourse(
            @PathVariable Long courseId){

        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId));
    }
}
