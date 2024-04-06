package org.example.springhomework003.controller;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.example.springhomework003.exception.AllNotFoundException;
import org.example.springhomework003.exception.AttendeeNotFoundException;
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

    @GetMapping("getAllAttendees")
    public ResponseEntity<?> getAllVenues(@RequestParam(required = false, defaultValue = "1") int numberPage,
                                          @RequestParam(required = false, defaultValue = "5") int NumberSize) {
        List<Attendee> attendees = attendeeService.getAllAttendees(numberPage, NumberSize);
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
        try {
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
        }catch (Exception e) {
            throw new AllNotFoundException("Attendee with id " + id + " does not found.");
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> createAttendee(@RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.postAttendee(attendeeRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Created Attendee Successfully.")
                        .code(201)
                        .payload(attendee)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttendee(@PathVariable Integer id, AttendeeRequest attendeeRequest){
        try {
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
        }catch (Exception e) {
            throw new AllNotFoundException("Attendee with id " + id + " does not found.");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendees(@PathVariable Integer id){
        try {
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
        }catch (Exception e) {
            throw new AllNotFoundException("Attendee with id " + id + " does not found.");
        }
    }

}
