package org.springdb.controller;

import org.springdb.model.Profile;
import org.springdb.model.Team;
import org.springdb.service.ProfileService;
import org.springdb.service.TeamService;
import org.springdb.wrapper.TeamMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;
    private final ProfileService profileService;

    @Autowired
    public TeamController(TeamService teamService, ProfileService profileService) {
        this.teamService = teamService;
        this.profileService = profileService;
    }

    @GetMapping("/get")
    public ResponseEntity getTeam(@RequestParam String id) {
        Optional<Team> existingTeam = teamService.getTeam(id);

        System.out.println("existingTeam: " + existingTeam);
        if (existingTeam.isPresent()) {
            return ResponseEntity.ok(existingTeam.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity createTeam(@RequestParam String name) {
        try {
            Team team = teamService.createTeam(name);
            return ResponseEntity.ok("Team created successfully, id: " + team.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while creating the team.");
        }
    }

    @PutMapping("/add_member")
    public ResponseEntity addMember(@RequestBody TeamMemberRequest request) {
        Optional<Team> existingTeam = teamService.getTeam(request.getTeamId());

        if (existingTeam.isPresent()) {
            Team team = (Team) existingTeam.get();

            Optional<Profile> existingProfile = profileService.getProfile(request.getProfileId());

            if (existingProfile.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            team.addMember((Profile)existingProfile.get());
            teamService.updateTeam(team);
            return ResponseEntity.ok("Members added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/remove_member")
    public ResponseEntity removeMember(@RequestBody TeamMemberRequest request) {
        Optional<Team> existingTeam = teamService.getTeam(request.getTeamId());

        if (existingTeam.isPresent()) {
            Team team = (Team) existingTeam.get();

            Optional<Profile> existingProfile = profileService.getProfile(request.getProfileId());

            if (existingProfile.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            team.removeMember((Profile)existingProfile.get());
            teamService.updateTeam(team);
            return ResponseEntity.ok("Members removed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
