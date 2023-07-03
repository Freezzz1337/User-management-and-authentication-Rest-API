package com.freezzz.services;

import com.freezzz.models.User;
import com.freezzz.repository.UserRepository;
import com.freezzz.util.LoginChangeException;
import com.freezzz.util.PasswordChangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userLoginVerificationAuthentication(String login) {
        return userRepository.findByLogin(login).isEmpty();
    }

    @Transactional
    public void updateUserData(String userId, User updateUser) {
        User user = userRepository.findById(Integer.valueOf(userId)).orElseThrow();

        updateUser.setId(user.getId());
        updateUser.setRole(user.getRole());
        updateUser.setPassword(user.getPassword());
        updateUser.setLogin(user.getLogin());

        userRepository.save(updateUser);
    }

    @Transactional
    public void updateUserCredential(String userId, String newPassword, String newLogin) throws PasswordChangeException, LoginChangeException {
        User user = userRepository.findById(Integer.valueOf(userId)).orElseThrow();

        if (user.getLogin().equals(newLogin))
            throw new LoginChangeException();

        if (new BCryptPasswordEncoder().matches(newPassword, user.getPassword()))
            throw new PasswordChangeException();

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setLogin(newLogin);

        userRepository.save(user);
    }
}
