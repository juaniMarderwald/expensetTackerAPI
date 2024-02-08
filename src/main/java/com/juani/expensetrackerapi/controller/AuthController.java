package com.juani.expensetrackerapi.controller;

import com.juani.expensetrackerapi.dto.CreateUserDto;
import com.juani.expensetrackerapi.entity.UserEntity;
import com.juani.expensetrackerapi.repository.UserRepository;
import com.juani.expensetrackerapi.service.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(){
//        return new ResponseEntity<String>("User is logged in", HttpStatus.OK);
//    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody CreateUserDto createUserDto){

        return ResponseEntity.ok(userService.createUser(createUserDto));
    }

}
