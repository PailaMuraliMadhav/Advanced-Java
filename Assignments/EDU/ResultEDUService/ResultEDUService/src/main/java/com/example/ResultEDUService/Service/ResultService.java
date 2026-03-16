package com.example.ResultEDUService.Service;

import com.example.ResultEDUService.Client.AttendanceClient;
import com.example.ResultEDUService.Client.StudentClient;
import com.example.ResultEDUService.Entity.Result;
import com.example.ResultEDUService.Repository.ResultRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class ResultService {

    private final ResultRepository resultRepository;
    private final StudentClient studentClient;
    private final AttendanceClient attendanceClient;

    public Result addResult(Result result) {
        return resultRepository.save(result);
    }

    public List<Result> getResultsByStudent(Long studentId) {
        return resultRepository.findByStudentId(studentId);
    }

    public List<Result> getResultsByCourse(Long courseId) {
        return resultRepository.findByCourseId(courseId);
    }

    public Result updateResult(Long id, Result updated) {
        Result existing = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found: " + id));
        existing.setMarksObtained(updated.getMarksObtained());
        existing.setMaxMarks(updated.getMaxMarks());
        existing.setExamType(updated.getExamType());
        return resultRepository.save(existing);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    @CircuitBreaker(name = "attendanceService", fallbackMethod = "getPerformanceFallback")
    public Map<String, Object> getStudentPerformance(Long studentId, Long courseId) {
        StudentClient.StudentResponse student = studentClient.getStudentById(studentId);
        Map<String, Object> attendance = attendanceClient.getAttendancePercentage(studentId, courseId);
        List<Result> results = resultRepository.findByStudentId(studentId);

        double totalMarks = results.stream().mapToDouble(Result::getMarksObtained).sum();
        double maxMarks = results.stream().mapToDouble(Result::getMaxMarks).sum();
        double gpa = maxMarks > 0 ? (totalMarks / maxMarks) * 10 : 0.0;

        Map<String, Object> performance = new HashMap<>();
        performance.put("student", student);
        performance.put("attendance", attendance);
        performance.put("results", results);
        performance.put("gpa", String.format("%.2f / 10.0", gpa));
        performance.put("totalExams", results.size());
        return performance;
    }

    public Map<String, Object> getPerformanceFallback(Long studentId, Long courseId, Exception ex) {
        log.warn("Circuit breaker triggered for attendance service: {}", ex.getMessage());
        List<Result> results = resultRepository.findByStudentId(studentId);
        Map<String, Object> performance = new HashMap<>();
        performance.put("studentId", studentId);
        performance.put("results", results);
        performance.put("attendance", "Attendance data currently unavailable");
        performance.put("fallback", true);
        return performance;
    }
}