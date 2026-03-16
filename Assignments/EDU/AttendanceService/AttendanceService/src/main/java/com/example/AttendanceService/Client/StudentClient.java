package com.example.AttendanceService.Client;

import com.example.AttendanceService.DTO.StudentDto;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
@FeignClient(name = "student-service", fallback = StudentClientFallback.class)
public interface StudentClient {

    @GetMapping("/students/{id}")
    StudentResponse getStudentById(@PathVariable("id") Long id);

    @Data
    class StudentResponse {
        private Long id;
        private String name;
        private String email;
        private String department;
        private Integer semester;
    }
}