package com.example.ResultEDUService.Client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class AttendanceClientFallback implements AttendanceClient {

    @Override
    public Map<String, Object> getAttendancePercentage(Long studentId, Long courseId) {
        log.warn("AttendanceService unavailable. Circuit breaker triggered.");
        return Map.of(
                "studentId", studentId,
                "courseId", courseId,
                "percentage", "Attendance data currently unavailable",
                "fallback", true
        );
    }
}

