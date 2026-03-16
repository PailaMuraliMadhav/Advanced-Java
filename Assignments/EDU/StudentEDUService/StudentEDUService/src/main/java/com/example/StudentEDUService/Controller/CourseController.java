package com.example.StudentEDUService.Controller;

import com.example.StudentEDUService.Entity.Course;
import com.example.StudentEDUService.Entity.Enrollment;
import com.example.StudentEDUService.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") Long id, @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/enroll")
    public ResponseEntity<Enrollment> enrollStudent(
            @RequestParam("studentId") Long studentId,
            @RequestParam("courseId") Long courseId) {
        return ResponseEntity.ok(courseService.enrollStudent(studentId, courseId));
    }

    @GetMapping("/students/{id}/courses")
    public ResponseEntity<List<Enrollment>> getStudentCourses(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getStudentCourses(id));
    }
}
