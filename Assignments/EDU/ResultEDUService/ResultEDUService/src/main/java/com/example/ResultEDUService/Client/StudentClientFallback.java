package com.example.ResultEDUService.Client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StudentClientFallback implements StudentClient {

    @Override
    public StudentResponse getStudentById(Long id) {
        log.warn("StudentService unavailable. Returning fallback for student id: {}", id);
        StudentResponse fallback = new StudentResponse();
        fallback.setId(id);
        fallback.setName("Student data currently unavailable");
        fallback.setEmail("N/A");
        fallback.setDepartment("N/A");
        return fallback;
    }
}
