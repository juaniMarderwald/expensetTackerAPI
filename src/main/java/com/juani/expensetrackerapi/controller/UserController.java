package com.juani.expensetrackerapi.controller;

import com.juani.expensetrackerapi.entity.UserEntity;
import com.juani.expensetrackerapi.entity.UserModel;
import com.juani.expensetrackerapi.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> readUser(@PathVariable Long id){
        return new ResponseEntity<UserEntity>(userService.readUser(id), HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<UserEntity> updateUser(@RequestBody UserModel user, @PathVariable Long id){
//        return new ResponseEntity<UserEntity>(userService.update(user, id), HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
