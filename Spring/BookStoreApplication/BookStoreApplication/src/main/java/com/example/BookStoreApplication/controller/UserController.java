package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.dto.UserDto;
import com.example.BookStoreApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto dto){
        UserDto registeredUser = userService.registerUser(dto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
    @PostMapping("/loginUser")
    public  ResponseEntity<UserDto> loginUser(@RequestBody UserDto login){
        UserDto  loginUser = userService.loginUser(login.getEmail(),login.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(loginUser);
    }

    @PutMapping("/updateUser/{id}")
    public  ResponseEntity<UserDto> UpdateUser(@PathVariable long id,@RequestBody @Valid UserDto dto){
        UserDto updatedUser = userService.updateUser(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @GetMapping("/getuser/{id}")
    public  ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto getuser = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(getuser);
    }


    @GetMapping("/getAll")
    public  ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> users = userService.getAllUsers();
        return  ResponseEntity.status(HttpStatus.OK).body(users);
    }
@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok("User dropped successfully!");
}





}
