package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.UserDto;
import com.example.BookStoreApplication.entity.User;
import com.example.BookStoreApplication.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ModelMapper modelMapper; // automattically matches fields from userDTO to User
    private final UserRespository userRepository;

    public UserDto registerUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
        User registered = userRepository.save(user);
        return modelMapper.map(registered,UserDto.class);
    }
    public UserDto loginUser(String email,String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with Email"));
        if(!user.getPassword().equalsIgnoreCase(password)){
            throw  new RuntimeException("Invalid Password");
        }
        return modelMapper.map(user,UserDto.class);
    }

    public UserDto updateUser(Long id,UserDto userDto){
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        existingUser.setUsername(userDto.getUsername());

        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());
        User updated = userRepository.save(existingUser);
        return modelMapper.map(updated,UserDto.class);
    }
    // Change from (Long id, UserDto userDto) to just (Long id)
    public String deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found by id");
        }
        userRepository.deleteById(id);
        return "User deleted Successfully";
    }
    public  UserDto getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user,UserDto.class);
    }

    public  List<UserDto> getAllUsers(){
        return  userRepository.findAll().stream()
                .map(u -> modelMapper.map(u,UserDto.class)).collect(Collectors.toList());
    }
}
