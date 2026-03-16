package com.example.StudentEDUService.Service;


import com.example.StudentEDUService.Config.JwtService;
import com.example.StudentEDUService.DTO.AuthDto;
import com.example.StudentEDUService.Entity.Student;
import com.example.StudentEDUService.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthDto.AuthResponse register(AuthDto.RegisterRequest request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }

        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .department(request.getDepartment())
                .semester(request.getSemester())
                .build();

        Student saved = studentRepository.save(student);

        UserDetails userDetails = User.withUsername(saved.getEmail())
                .password(saved.getPassword())
                .roles("STUDENT")
                .build();

        String token = jwtService.generateToken(userDetails);
        return new AuthDto.AuthResponse(token, saved.getEmail(), saved.getName(), saved.getId());
    }

    public AuthDto.AuthResponse login(AuthDto.LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        UserDetails userDetails = User.withUsername(student.getEmail())
                .password(student.getPassword())
                .roles("STUDENT")
                .build();

        String token = jwtService.generateToken(userDetails);
        return new AuthDto.AuthResponse(token, student.getEmail(), student.getName(), student.getId());
    }
}
