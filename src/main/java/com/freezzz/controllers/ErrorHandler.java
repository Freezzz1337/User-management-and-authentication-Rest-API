package com.freezzz.controllers;

import com.freezzz.util.AuthenticationAndRegistrationErrorResponse;
import com.freezzz.util.LoginChangeException;
import com.freezzz.util.LoginNotFoundException;
import com.freezzz.util.PasswordChangeException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<AuthenticationAndRegistrationErrorResponse> handleException(MethodArgumentNotValidException ex) {
        AuthenticationAndRegistrationErrorResponse errorResponse = new AuthenticationAndRegistrationErrorResponse(
                ex.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(", ")),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AuthenticationAndRegistrationErrorResponse> handleException(LoginNotFoundException ex) {
        AuthenticationAndRegistrationErrorResponse errorResponse = new AuthenticationAndRegistrationErrorResponse(
                "User with this login was not found",
                System.currentTimeMillis()

        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AuthenticationAndRegistrationErrorResponse> handleException(BadCredentialsException ex) {
        AuthenticationAndRegistrationErrorResponse errorResponse = new AuthenticationAndRegistrationErrorResponse(
                "Password entered incorrectly",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AuthenticationAndRegistrationErrorResponse> handleException(LoginChangeException ex){
        AuthenticationAndRegistrationErrorResponse errorResponse = new AuthenticationAndRegistrationErrorResponse(
                "The login must be different from the previous one",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AuthenticationAndRegistrationErrorResponse> handleException(PasswordChangeException ex){
        AuthenticationAndRegistrationErrorResponse errorResponse = new AuthenticationAndRegistrationErrorResponse(
                "The password must be different from the previous one",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
