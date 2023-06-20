package com.freezzz.controllers;

import com.freezzz.dto.AuthenticationRequestDTO;
import com.freezzz.auth.AuthenticationResponse;
import com.freezzz.services.AuthenticationService;
import com.freezzz.dto.RegisterRequestDTO;
import com.freezzz.util.AuthenticationAndRegistrationErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

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
}
