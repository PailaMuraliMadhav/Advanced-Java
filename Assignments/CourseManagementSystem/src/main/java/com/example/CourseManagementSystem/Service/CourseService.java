package com.example.CourseManagementSystem.Service;

import com.example.CourseManagementSystem.Entity.Course;
import com.example.CourseManagementSystem.Entity.User;
import com.example.CourseManagementSystem.Repository.CourseRepository;
import com.example.CourseManagementSystem.Repository.UserRepository;
import com.example.CourseManagementSystem.dto.CourseRequestDto;
import com.example.CourseManagementSystem.dto.CourseResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CourseResponseDto createCourse(CourseRequestDto request){

        Course course = modelMapper.map(request, Course.class);

        User instructor = userRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setInstructor(instructor);

        courseRepository.save(course);

        return modelMapper.map(course, CourseResponseDto.class);
    }

    public CourseResponseDto updateCourse(Long id, CourseRequestDto request){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setLevel(request.getLevel());

        courseRepository.save(course);
        return modelMapper.map(course, CourseResponseDto.class);
    }
    @CacheEvict(value = "courses",allEntries = true)
    public void deleteCourse(Long id)
    {
        courseRepository.deleteById(id);

    }

    public CourseResponseDto getCourseById(Long id){

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return modelMapper.map(course, CourseResponseDto.class);
    }
    @Cacheable("courses")
    public Page<CourseResponseDto> getAllCourses(Pageable pageable){

        Page<Course> courses = courseRepository.findAll(pageable);

        return courses.map(course -> modelMapper.map(course, CourseResponseDto.class));
    }

}
