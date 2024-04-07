package org.example.springhomework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.springhomework003.exception.AllNotFoundException;
import org.example.springhomework003.model.Event;
import org.example.springhomework003.model.dto.request.EventRequest;
import org.example.springhomework003.model.dto.response.ApiResponse;
import org.example.springhomework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVenues(
            @RequestParam(defaultValue = "1")
            @Positive(message = "must be greater than 0") int numberPage,
            @Positive(message = "must be greater than 0")
            @RequestParam(defaultValue = "3") int numberSize) {
        List<Event> event = eventService.getAllEvents(numberPage, numberSize);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Get all Events Successfully.")
                        .code(200)
                        .payload(event)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Integer id) {
            Event event = eventService.getEventById(id);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Get Event Successfully.")
                            .code(200)
                            .payload(event)
                            .status(HttpStatus.OK)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }
    @PostMapping
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventRequest eventRequest) {
        Event event = eventService.createEvent(eventRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("The event has been successfully added.")
                        .code(200)
                        .payload(event)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Integer id,@Valid  @RequestBody EventRequest eventRequest) {
        Event event = eventService.updateEvent(id, eventRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Update Event Successfully.")
                        .code(200)
                        .payload(event)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer id) {
        eventService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Delete Event Successfully.")
                        .code(200)
                        .payload(null)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
