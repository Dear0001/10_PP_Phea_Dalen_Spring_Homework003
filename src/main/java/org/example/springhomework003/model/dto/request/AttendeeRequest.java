package org.example.springhomework003.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendeeRequest {
    @NotBlank(message = "Attendee name is required.")
    @NotNull(message = "Attendee name is required.")
    private String attendeeName;
    @Email(message = "Invalid email address format.")
    private String attendeeEmail;
}
