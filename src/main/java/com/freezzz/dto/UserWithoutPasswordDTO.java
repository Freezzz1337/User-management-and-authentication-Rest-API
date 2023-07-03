package com.freezzz.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWithoutPasswordDTO {
    @NotBlank(message = "name field must not be empty")
    private String name;
    @Min(value = 12,message = "age should be more than 12")
    private int age;
    @NotBlank(message = "email field must not be empty")
    @Email
    private String email;
}
