package org.springub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springub.model.User;
import org.springub.repository.UserRepository;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        String salt = generateSalt();
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash() + salt);
        user.setSalt(salt);
        user.setPasswordHash(hashedPassword);
        return userRepository.save(user);
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }


    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
