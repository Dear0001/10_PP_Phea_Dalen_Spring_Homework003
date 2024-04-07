package org.example.springhomework003.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VenueRequest {
    @NotNull(message = "Venue name is required.")
    @NotBlank(message = "Venue name is required.")
    private String venueName;
    @NotNull(message = "Venue location is required.")
    @NotBlank(message = "Venue location is required.")
    private String venueLocation;
}
