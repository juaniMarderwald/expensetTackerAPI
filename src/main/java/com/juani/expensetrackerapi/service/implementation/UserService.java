package com.juani.expensetrackerapi.service.implementation;

import com.juani.expensetrackerapi.dto.CreateUserDto;
import com.juani.expensetrackerapi.entity.Role;
import com.juani.expensetrackerapi.entity.UserEntity;
import com.juani.expensetrackerapi.entity.UserModel;
import com.juani.expensetrackerapi.enums.ERole;
import com.juani.expensetrackerapi.exception.ItemAlreadyExistException;
import com.juani.expensetrackerapi.exception.ResourceNotFoundException;
import com.juani.expensetrackerapi.repository.UserRepository;
import com.juani.expensetrackerapi.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(CreateUserDto createUserDto) {

        if(userRepository.existsByEmail(createUserDto.getEmail())){
            throw new ItemAlreadyExistException("User is already register with email: " + createUserDto.getEmail());
        }

        Set<Role> roles = createUserDto.getRoles()
                .stream().map( role -> Role.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDto.getUsername())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .age(createUserDto.getAge())
                .email(createUserDto.getEmail())
                .roles(roles)
                .build();

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity readUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for the id: " + id));
    }

//    @Override
//    public UserEntity update(UserModel user, Long id) throws ResourceNotFoundException{
//        UserEntity existingUserEntity = readUser(id);
//        existingUserEntity.setUsername(user.getName() != null ? user.getName() : existingUserEntity.getUsername());
//        existingUserEntity.setEmail(user.getEmail() != null ? user.getEmail() : existingUserEntity.getEmail());
//        existingUserEntity.setPassword(user.getPassword() != null ? user.getPassword() : existingUserEntity.getPassword());
//        existingUserEntity.setAge(user.getAge() != null ? user.getAge() : existingUserEntity.getAge());
//
//        return userRepository.save(existingUserEntity);
//    }

    @Override
    public void delete(Long id) {
        UserEntity userEntity = readUser(id);
        userRepository.delete(userEntity);
    }
}
