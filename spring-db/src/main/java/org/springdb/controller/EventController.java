package org.springdb.controller;

import org.springdb.model.Event;
import org.springdb.model.Team;
import org.springdb.service.EventService;
import org.springdb.service.TeamService;
import org.springdb.wrapper.EventWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final TeamService teamService;
    private final EventService eventService;

    @Autowired
    public EventController(TeamService teamService, EventService eventService) {
        this.teamService = teamService;
        this.eventService = eventService;
    }

    @GetMapping("/get")
    public ResponseEntity getEvent(@RequestParam String id) {
        Optional<Event> existingEvent = eventService.getEvent(id);

        if (existingEvent.isPresent()) {
            return ResponseEntity.ok(existingEvent.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity createEvent(@RequestBody EventWrapper request) {
        try {
            Optional<Team> t1 = teamService.getTeam(request.getTeam1());
            Optional<Team> t2 = teamService.getTeam(request.getTeam2());

            if (t1.isEmpty() || t2.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Event event = new Event(request.getName(), request.getDescription(), t1.get(), t2.get());
            Event newEvent = eventService.createEvent(event);
            return ResponseEntity.ok("Event created successfully, id: " + newEvent.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while creating the event.");
        }
    }
}
