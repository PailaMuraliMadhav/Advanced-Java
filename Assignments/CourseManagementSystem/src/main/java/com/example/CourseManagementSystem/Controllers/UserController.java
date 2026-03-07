package com.example.CourseManagementSystem.Controllers;

import com.example.CourseManagementSystem.Service.UserService;
import com.example.CourseManagementSystem.dto.LoginRequestDto;
import com.example.CourseManagementSystem.dto.RegisterRequestDto;
import com.example.CourseManagementSystem.dto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(
            @Valid @RequestBody RegisterRequestDto request){

        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @Valid @RequestBody LoginRequestDto request){

        return ResponseEntity.ok(userService.loginUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){

        return ResponseEntity.ok(userService.getUserById(id));
    }
}
