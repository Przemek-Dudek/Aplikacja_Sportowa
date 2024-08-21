package org.springub.controller;

import org.springub.model.User;
import org.springub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springub.wrapper.LoginForm;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody LoginForm body) {
        try {
            userService.createUser(body);
            return ResponseEntity.ok("User created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred while creating the user. " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editUser(@RequestBody LoginForm body) {
        User user = userService.getUser(body.getLogin(), body.getPassword());
        if (user != null) {
            try {
                userService.editUser(body);
                return ResponseEntity.ok("User updated successfully.");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(500).body("An unexpected error occurred while updating the user.");
            }
        } else {
            return ResponseEntity.status(401).body("Invalid login or password.");
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateUser(@RequestBody LoginForm body) {
        User user = userService.getUser(body.getLogin(), body.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user.getId());
        } else {
            return ResponseEntity.status(401).body("Invalid login or password.");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<User> getUser(@RequestParam String login) {
        Optional<User> user = userService.findByLogin(login);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
