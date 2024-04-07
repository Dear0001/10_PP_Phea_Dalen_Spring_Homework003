package org.example.springhomework003.service.attendeeSeriveImpl;

import org.example.springhomework003.exception.AllNotFoundException;
import org.example.springhomework003.model.Attendee;
import org.example.springhomework003.model.dto.request.AttendeeRequest;
import org.example.springhomework003.repository.AttendeeRepository;
import org.example.springhomework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }


    @Override
    public List<Attendee> getAllAttendees(int numberPage, int numberSize) {
        return attendeeRepository.getAllAttendees(numberPage, numberSize);
    }
    @Override
    public Attendee getAttendeeById(Integer id) {
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if(attendee == null){
            throw new AllNotFoundException("Attendee with id " + id + " does not exist.");
        }
        return attendee;
    }

    @Override
    public Attendee postAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.postAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendee(Integer id, AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeRepository.updateAttendee(id,attendeeRequest);
        if(attendee == null){
            throw new AllNotFoundException("Attendee with id " + id + " does not found.");
        }
        return attendee;
    }

    @Override
    public void deleteAttendee(Integer id) {
        if(attendeeRepository.getAttendeeById(id) == null){
            throw new AllNotFoundException("Attendee with id " + id + " does not found.");
        }
        attendeeRepository.deleteAttendee(id);
    }

}
