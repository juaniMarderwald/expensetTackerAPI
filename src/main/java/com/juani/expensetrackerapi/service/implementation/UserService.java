package com.juani.expensetrackerapi.service.implementation;

import com.juani.expensetrackerapi.entity.User;
import com.juani.expensetrackerapi.entity.UserModel;
import com.juani.expensetrackerapi.exception.ItemAlreadyExistException;
import com.juani.expensetrackerapi.exception.ResourceNotFoundException;
import com.juani.expensetrackerapi.repository.UserRepository;
import com.juani.expensetrackerapi.service.UserServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ItemAlreadyExistException("User is already register with email: " + user.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for the id: " + id));
    }

    @Override
    public User update(UserModel user, Long id) throws ResourceNotFoundException{
        User existingUser = readUser(id);
        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
        existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());

        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        User user = readUser(id);
        userRepository.delete(user);
    }
}
