package com.juani.expensetrackerapi.service;

import com.juani.expensetrackerapi.entity.User;
import com.juani.expensetrackerapi.entity.UserModel;

public interface UserServiceInterface {

    User createUser(UserModel user);

    User readUser(Long id );

    User update(UserModel user, Long id);

    void delete(Long id);
}
