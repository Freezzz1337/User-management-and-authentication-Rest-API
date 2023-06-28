package com.freezzz.controllers;

import com.freezzz.util.AuthenticationAndRegistrationErrorResponse;
import com.freezzz.util.LoginNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
