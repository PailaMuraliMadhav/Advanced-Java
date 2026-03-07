package com.example.CourseManagementSystem.Controllers;

import com.example.CourseManagementSystem.Service.CourseService;
import com.example.CourseManagementSystem.dto.CourseRequestDto;
import com.example.CourseManagementSystem.dto.CourseResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(
            @Valid @RequestBody CourseRequestDto request){

        return ResponseEntity.ok(courseService.createCourse(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDto request){

        return ResponseEntity.ok(courseService.updateCourse(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){

        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id){

        return ResponseEntity.ok(courseService.getCourseById(id));
    }


    @GetMapping("/allcourses")
    public ResponseEntity<Page<CourseResponseDto>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "title") String sort){

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }
}
