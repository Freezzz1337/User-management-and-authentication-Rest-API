package com.freezzz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {
    @NotBlank(message = "login field must not be empty")
//    @Size(min = 5, max = 30, message = "login must be at least 3 characters and not more than 30")
    private String login;
    @NotBlank(message = "password field must not be empty")
//    @Size(min = 8, max = 50, message = "password must be at least 8 characters and not more than 50")
    private String password;
}
