package org.example.springhomework003.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendeeRequest {
    private String attendeeName;
    private String attendeeEmail;
}
