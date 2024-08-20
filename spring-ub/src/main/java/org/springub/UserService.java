package org.springub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        // Generate salt and hash the password
        String salt = "randomSalt"; // You should generate a real salt
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash() + salt);

        user.setSalt(salt);
        user.setPasswordHash(hashedPassword);
        return userRepository.save(user);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
