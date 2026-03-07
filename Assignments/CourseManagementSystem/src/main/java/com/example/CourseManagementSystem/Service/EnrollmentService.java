package com.example.CourseManagementSystem.Service;

import com.example.CourseManagementSystem.Entity.Course;
import com.example.CourseManagementSystem.Entity.Enrollment;
import com.example.CourseManagementSystem.Entity.User;
import com.example.CourseManagementSystem.Repository.CourseRepository;
import com.example.CourseManagementSystem.Repository.EnrollmentRepository;
import com.example.CourseManagementSystem.Repository.UserRepository;
import com.example.CourseManagementSystem.dto.EnrollmentRequestDto;
import com.example.CourseManagementSystem.dto.EnrollmentResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public EnrollmentResponseDto enrollStudent(EnrollmentRequestDto request){
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        enrollmentRepository.save(enrollment);
        return modelMapper.map(enrollment, EnrollmentResponseDto.class);
    }

    public List<EnrollmentResponseDto> getEnrollmentsByStudent(Long studentId){

        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(e -> modelMapper.map(e, EnrollmentResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<EnrollmentResponseDto> getEnrollmentsByCourse(Long courseId){

        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(e -> modelMapper.map(e, EnrollmentResponseDto.class))
                .collect(Collectors.toList());
    }
}
