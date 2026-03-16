package com.example.ResultEDUService.Controller;

import com.example.ResultEDUService.Entity.Result;
import com.example.ResultEDUService.Service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<Result> addResult(@RequestBody Result result) {
        return ResponseEntity.ok(resultService.addResult(result));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Result>> getByStudent(@PathVariable("studentId") Long studentId) {
        return ResponseEntity.ok(resultService.getResultsByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Result>> getByCourse(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(resultService.getResultsByCourse(courseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable("id") Long id, @RequestBody Result result) {
        return ResponseEntity.ok(resultService.updateResult(id, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable("id") Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/performance")
    public ResponseEntity<Map<String, Object>> getPerformance(
            @RequestParam("studentId") Long studentId,
            @RequestParam("courseId") Long courseId) {
        return ResponseEntity.ok(resultService.getStudentPerformance(studentId, courseId));
    }
}
