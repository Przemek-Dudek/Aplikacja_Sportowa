package org.springdb.controller;

import org.springdb.model.Profile;
import org.springdb.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/get")
    public ResponseEntity<Profile> getProfile(@RequestParam String id) {
        Optional<Profile> existingProfile = profileService.getProfile(id);

        if (existingProfile.isPresent()) {
            return ResponseEntity.ok(existingProfile.get());
        } else {
            Profile newProfile = new Profile(id);
            Profile createdProfile = profileService.createProfile(newProfile);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
        }
    }
}
