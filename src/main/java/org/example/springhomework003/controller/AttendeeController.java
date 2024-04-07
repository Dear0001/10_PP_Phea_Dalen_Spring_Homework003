package org.example.springhomework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.springhomework003.model.Attendee;
import org.example.springhomework003.model.dto.request.AttendeeRequest;
import org.example.springhomework003.model.dto.response.ApiResponse;
import org.example.springhomework003.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/attendee")
public class AttendeeController {

    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVenues(
            @RequestParam(defaultValue = "1")
            @Positive(message = "must be greater than 0") int numberPage,
            @Positive(message = "must be greater than 0")
            @RequestParam(defaultValue = "3") int numberSize) {
        List<Attendee> attendees = attendeeService.getAllAttendees(numberPage, numberSize);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Get all Attendees Successfully.")
                        .code(200)
                        .payload(attendees)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendeeById(@PathVariable Integer id){
            Attendee attendee = attendeeService.getAttendeeById(id);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Get Attendee by id Successfully.")
                            .code(200)
                            .payload(attendee)
                            .status(HttpStatus.OK)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }

    @PostMapping
    public ResponseEntity<?> createAttendee(@Valid @RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.postAttendee(attendeeRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("The attendee has been successfully added.")
                        .code(201)
                        .payload(attendee)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttendee(@PathVariable Integer id, @Valid AttendeeRequest attendeeRequest){
            Attendee attendee = attendeeService.updateAttendee(id, attendeeRequest);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Updated Attendee successfully.")
                            .code(200)
                            .payload(attendee)
                            .status(HttpStatus.OK)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendees(@PathVariable Integer id){
            attendeeService.deleteAttendee(id);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Deleted Attendee successfully.")
                            .code(200)
                            .payload(null)
                            .status(HttpStatus.OK)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }

}
