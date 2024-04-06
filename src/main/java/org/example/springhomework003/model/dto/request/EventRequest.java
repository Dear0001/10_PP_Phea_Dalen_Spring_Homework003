package org.example.springhomework003.model.dto.request;


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
public class EventRequest {
    private String eventName;
    private LocalDateTime eventDateTime;
    private Integer venueId;
    private List<Integer> attendeeId;
}