package org.example.springhomework003.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Venue {
    private Integer venueId;
    private String venueName;
    private String venueLocation;
}
