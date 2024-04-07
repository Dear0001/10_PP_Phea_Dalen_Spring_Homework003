package org.example.springhomework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.example.springhomework003.exception.AllNotFoundException;
import org.example.springhomework003.model.Venue;
import org.example.springhomework003.model.dto.request.VenueRequest;
import org.example.springhomework003.model.dto.response.ApiResponse;
import org.example.springhomework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v2/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVenues(
            @RequestParam(defaultValue = "1")
            @Positive(message = "must be greater than 0") int numberPage,
            @Positive(message = "must be greater than 0")
            @RequestParam(defaultValue = "3") int numberSize) {

        List<Venue> venues = venueService.getAllVenues(numberPage, numberSize);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Get all Venues Successfully.")
                        .code(200)
                        .payload(venues)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVenueById(@PathVariable Integer id) {
            Venue venue = venueService.getVenueById(id);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Get all Venues by id Successfully.")
                            .code(200)
                            .payload(venue)
                            .status(HttpStatus.OK)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
        }

    @PostMapping
    public ResponseEntity<?> postVenue(@Valid @RequestBody VenueRequest venueRequest){
        Venue venue = venueService.postVenue(venueRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("The venue has been successfully added.")
                        .code(201)
                        .payload(venue)
                        .status(HttpStatus.CREATED)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenue(@PathVariable Integer id,@Valid @RequestBody VenueRequest venueRequest ){
            Venue venue = venueService.updateVenue(id, venueRequest);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Update Venue Successfully.")
                            .code(200)
                            .payload(venue)
                            .status(HttpStatus.OK)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenue(@PathVariable Integer id){
            venueService.deleteVenue(id);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Delete Venue Successfully.")
                            .code(200)
                            .status(HttpStatus.OK)
                            .payload(null)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }
}
