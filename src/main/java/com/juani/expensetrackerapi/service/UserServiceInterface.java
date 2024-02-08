package com.juani.expensetrackerapi.service;

import com.juani.expensetrackerapi.dto.CreateUserDto;
import com.juani.expensetrackerapi.entity.UserEntity;
import com.juani.expensetrackerapi.entity.UserModel;

public interface UserServiceInterface {

    UserEntity createUser(CreateUserDto createUserDto);

    UserEntity readUser(Long id );

//    UserEntity update(UserModel user, Long id);

    void delete(Long id);
}
