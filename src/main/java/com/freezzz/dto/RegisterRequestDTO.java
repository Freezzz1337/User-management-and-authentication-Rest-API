package com.freezzz.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    @NotBlank(message = "name field must not be empty")
    private String name;
    @NotNull(message = "age field must not be empty")
    private int age;
    @NotBlank(message = "login field must not be empty")
    @Size(min = 6, max = 30, message = "login must be at least 3 characters and not more than 30")
    private String login;
    @NotBlank(message = "email field must not be empty")
    @Email
    private String email;
    @NotBlank(message = "password field must not be empty")
    @Size(min = 8, max = 50, message = "password must be at least 8 characters and not more than 50")
    private String password;
}
