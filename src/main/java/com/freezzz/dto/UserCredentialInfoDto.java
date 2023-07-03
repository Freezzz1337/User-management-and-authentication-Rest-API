package com.freezzz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredentialInfoDto {

    @NotBlank(message = "login field must not be empty")
    @Size(min = 6, max = 30, message = "login must be at least 6 characters and not more than 30")
    private String login;
    @NotBlank(message = "password field must not be empty")
    @Size(min = 8, max = 256, message = "password must be at least 8 characters and not more than 256")
    private String password;
}
