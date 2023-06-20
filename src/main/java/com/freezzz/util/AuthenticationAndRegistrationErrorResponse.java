package com.freezzz.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationAndRegistrationErrorResponse {

    private String message;
    private long timestamp;
}
