package com.juani.expensetrackerapi.dto;

import com.juani.expensetrackerapi.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class CreateUserDto {

    @NotNull(message = "Username is required")
    @Size(min = 3, message = "Username must have at least 3 characters")
    @NotBlank(message = "username must not be blank")
    private String username;

    @NotNull(message = "Email is required")
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email name must not be blank")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password should be at least 8 characters")
    @NotBlank(message = "Password name must not be blank")
    private String password;

    private Long age = 0L;


    private Set<String> roles;

}
