package org.example.springhomework003.service;

import org.example.springhomework003.model.Attendee;
import org.example.springhomework003.model.dto.request.AttendeeRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendeeService {

    List<Attendee> getAllAttendees(int numberPage, int numberSize);

    Attendee getAttendeeById(Integer id);

    Attendee postAttendee(AttendeeRequest attendeeRequest);

    Attendee updateAttendee(Integer id, AttendeeRequest attendeeRequest);

    void deleteAttendee(Integer id);

}
