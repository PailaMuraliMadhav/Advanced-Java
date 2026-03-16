package com.example.StudentEDUService.Repository;

import com.example.StudentEDUService.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByEmail(String email);

    boolean existsByEmail(String email);
}
