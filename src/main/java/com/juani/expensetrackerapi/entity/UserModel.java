package com.juani.expensetrackerapi.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {

    @NotNull(message = "User name is required")
    @Size(min = 3, message = "User name must have at least 3 characters")
    @NotBlank(message = "User name must not be blank")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;

    private Long age = 0L;
}
