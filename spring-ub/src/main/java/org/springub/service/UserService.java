package org.springub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springub.model.User;
import org.springub.repository.UserRepository;
import org.springub.wrapper.LoginForm;

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

    public User createUser(LoginForm data) {
        Optional<User> existingUser = userRepository.findByLogin(data.getLogin());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Login already in use");
        }

        String salt = generateSalt();
        String hashedPassword = passwordEncoder.encode(data.getPassword() + salt);

        User user = new User(data.getLogin(), data.getFirstName(), data.getLastName(), data.getEmail(), hashedPassword, salt);
        return userRepository.save(user);
    }

    public User editUser(LoginForm data) {
        Optional<User> userOptional = userRepository.findByLogin(data.getLogin());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(data.getFirstName());
            user.setLastName(data.getLastName());
            user.setEmail(data.getEmail());
            if (data.getNewPassword() != null && !data.getNewPassword().isEmpty()) {
                user.setPasswordHash(passwordEncoder.encode(data.getNewPassword() + user.getSalt()));
            }
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("User not found");
    }

    public User getUser(String login, String password) {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password + user.getSalt(), user.getPasswordHash())) {
                return user;
            }
        }
        return null;
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

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}
