package com.freezzz.controllers;

import com.freezzz.dto.AuthenticationRequestDTO;
import com.freezzz.auth.AuthenticationResponse;
import com.freezzz.services.AuthenticationService;
import com.freezzz.dto.RegisterRequestDTO;
import com.freezzz.services.UserService;
import com.freezzz.util.AuthenticationAndRegistrationErrorResponse;
import com.freezzz.util.LoginNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody AuthenticationRequestDTO request) throws LoginNotFoundException {

        if (userService.userLoginVerificationAuthentication(request.getLogin())) {
            throw new LoginNotFoundException();
        }

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

    @ExceptionHandler
    public ResponseEntity<AuthenticationAndRegistrationErrorResponse> handleException(LoginNotFoundException ex) {
        AuthenticationAndRegistrationErrorResponse errorResponse = new AuthenticationAndRegistrationErrorResponse(
                "User with this login was not found",
                System.currentTimeMillis()

        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
