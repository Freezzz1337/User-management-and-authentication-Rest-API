package com.freezzz.services;

import com.freezzz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userLoginVerificationAuthentication(String login){
        return userRepository.findByLogin(login).isPresent();
    }


}
