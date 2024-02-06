package com.juani.expensetrackerapi.controller;

import com.juani.expensetrackerapi.entity.User;
import com.juani.expensetrackerapi.entity.UserModel;
import com.juani.expensetrackerapi.service.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserModel user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id){
        return new ResponseEntity<User>(userService.readUser(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserModel user, @PathVariable Long id){
        return new ResponseEntity<User>(userService.update(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
