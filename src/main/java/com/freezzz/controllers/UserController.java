package com.freezzz.controllers;


import com.freezzz.config.JwtAuthenticationFilter;
import com.freezzz.dto.AuthenticationRequestDTO;
import com.freezzz.dto.UserCredentialInfoDto;
import com.freezzz.dto.UserWithoutPasswordDTO;
import com.freezzz.models.User;
import com.freezzz.services.UserService;
import com.freezzz.util.LoginChangeException;
import com.freezzz.util.LoginNotFoundException;
import com.freezzz.util.PasswordChangeException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/personal-info-editing")
    public ResponseEntity<String> userDataChanges(@Valid @RequestBody UserWithoutPasswordDTO userWithoutPasswordDTO) {
        userService.updateUserData(JwtAuthenticationFilter.userId, convertToUser(userWithoutPasswordDTO));
        return ResponseEntity.ok("Changes completed successfully!");
    }

    @PostMapping("/credentials-editing")
    public ResponseEntity<String> userCredentialChanges(@Valid @RequestBody UserCredentialInfoDto userCredentialInfoDto) throws PasswordChangeException, LoginChangeException {

        userService.updateUserCredential(JwtAuthenticationFilter.userId, userCredentialInfoDto.getPassword(), userCredentialInfoDto.getLogin());

        return ResponseEntity.ok("Changes completed successfully!");
    }

    private User convertToUser(UserWithoutPasswordDTO userWithoutPasswordDTO) {
        return modelMapper.map(userWithoutPasswordDTO, User.class);
    }
}
