package org.springdb.controller;

import org.springdb.model.Profile;
import org.springdb.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

//    @PostMapping("/create")
//    public ResponseEntity<String> createUser(@RequestBody LoginForm body) {
//
//    }
//
//    @PutMapping("/edit")
//    public ResponseEntity<String> editUser(@RequestBody LoginForm body) {
//
//    }
//
//    @PostMapping("/validate")
//    public ResponseEntity<String> validateUser(@RequestBody LoginForm body) {
//
//    }
//
//    @GetMapping("/get")
//    public ResponseEntity<Profile> getUser(@RequestParam String login) {
//
//    }
}
