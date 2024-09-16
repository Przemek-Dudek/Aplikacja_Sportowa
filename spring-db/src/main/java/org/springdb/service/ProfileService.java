package org.springdb.service;

import org.springdb.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springdb.repository.ProfileRepository;

import java.util.Optional;

@Service
public class  ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Optional<Profile> getProfile(String id) {
        return profileRepository.findById(id);
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile increaseTrustLevel(String id, Integer levelChange) {
        Optional<Profile> existingProfile = profileRepository.findById(id);
        if (existingProfile.isPresent()) {
            Profile profileToUpdate = existingProfile.get();
            profileToUpdate.increaseTrustLevel(levelChange);
            return profileRepository.save(profileToUpdate);
        } else {
            throw new IllegalArgumentException("Profile not found");
        }
    }

    public Profile increaseElo(String id, Integer eloChange) {
        Optional<Profile> existingProfile = profileRepository.findById(id);
        if (existingProfile.isPresent()) {
            Profile profileToUpdate = existingProfile.get();
            profileToUpdate.increaseElo(eloChange);
            return profileRepository.save(profileToUpdate);
        } else {
            throw new IllegalArgumentException("Profile not found");
        }
    }
}
