package com.freezzz.controllers;

import com.freezzz.dto.AuthenticationRequestDTO;
import com.freezzz.auth.AuthenticationResponse;
import com.freezzz.services.AuthenticationService;
import com.freezzz.dto.RegisterRequestDTO;
import com.freezzz.services.UserService;
import com.freezzz.util.LoginNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
