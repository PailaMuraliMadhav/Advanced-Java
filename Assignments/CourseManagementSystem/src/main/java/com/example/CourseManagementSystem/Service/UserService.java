package com.example.CourseManagementSystem.Service;

import com.example.CourseManagementSystem.Entity.User;

import com.example.CourseManagementSystem.Repository.UserRepository;
import com.example.CourseManagementSystem.dto.LoginRequestDto;
import com.example.CourseManagementSystem.dto.RegisterRequestDto;
import com.example.CourseManagementSystem.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponseDto registerUser(RegisterRequestDto request){

        User user = modelMapper.map(request, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    public String loginUser(LoginRequestDto request){
        return "Login Successful";
    }

    public UserResponseDto getUserById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return modelMapper.map(user, UserResponseDto.class);
    }
}
