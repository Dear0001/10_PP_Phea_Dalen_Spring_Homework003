package org.example.springhomework003.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Event {
    private Integer eventId;
    private String eventName;
    private LocalDateTime eventDateTime;
    private Venue venue;
    private List<Attendee> attendees;
}
