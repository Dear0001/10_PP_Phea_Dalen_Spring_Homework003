package org.example.springhomework003.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "Event name is required.")
    @NotBlank(message = "Event name is required.")
    private String eventName;
    private LocalDateTime eventDateTime;
    private Integer venueId;
    private List<Integer> attendeeId;
}