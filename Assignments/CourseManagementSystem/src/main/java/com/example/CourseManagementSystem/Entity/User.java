package com.example.CourseManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String fullName;
    @Column(unique = true)
    private String email;
    private  String password;
    @Enumerated(EnumType.STRING)
    private  Role role;
    private  String profilePicture;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "instructor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Course> courses;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Enrollment>enrollments;


}
