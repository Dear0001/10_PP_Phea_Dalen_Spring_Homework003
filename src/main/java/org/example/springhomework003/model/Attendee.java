package org.example.springhomework003.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendee {
    private Integer attendeeId;
    private String attendeeName;
    private String attendeeEmail;
}
