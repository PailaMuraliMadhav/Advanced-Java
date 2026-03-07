package com.example.CourseManagementSystem.Repository;

import com.example.CourseManagementSystem.Entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial,Long> {
    List<CourseMaterial> findByCourseId(Long courseId);

}
