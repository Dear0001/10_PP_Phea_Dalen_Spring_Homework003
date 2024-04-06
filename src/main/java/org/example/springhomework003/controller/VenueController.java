package org.example.springhomework003.controller;

import org.example.springhomework003.exception.AllNotFoundException;
import org.example.springhomework003.model.Venue;
import org.example.springhomework003.model.dto.request.VenueRequest;
import org.example.springhomework003.model.dto.response.ApiResponse;
import org.example.springhomework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/venue")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("getAllVenues")
    public ResponseEntity<?> getAllVenues(@RequestParam(required = false, defaultValue = "1") int numberPage,
                                          @RequestParam(required = false, defaultValue = "5") int NumberSize) {
        List<Venue> venue = venueService.getAllVenues(numberPage, NumberSize);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Get all Venues Successfully.")
                        .code(200)
                        .payload(venue)
                        .status(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVenueById(@PathVariable Integer id) {
        try {
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
        }catch (Exception e){
            throw new AllNotFoundException("Venue with id " + id + " does not exist.");
        }
    }

    @PostMapping("postVenue")
    public ResponseEntity<?> postVenue(@RequestBody VenueRequest venueRequest){
        Venue venue = venueService.postVenue(venueRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Post Venue Successfully.")
                        .code(201)
                        .payload(venue)
                        .status(HttpStatus.CREATED)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenue(@PathVariable Integer id, @RequestBody VenueRequest venueRequest ){
        try {
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
        }catch (Exception e){
            throw new AllNotFoundException("Venue with id " + id + " does not exist.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenue(@PathVariable Integer id){
        try {
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
        }catch (Exception e){
            throw new AllNotFoundException("Venue with id " + id + " does not exist.");

        }
    }
}
