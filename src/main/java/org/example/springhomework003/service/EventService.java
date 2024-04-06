package org.example.springhomework003.service;

import org.example.springhomework003.model.Event;
import org.example.springhomework003.model.dto.request.EventRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    List<Event> getAllEvents(int numberPage, int numberSize);

    Event getEventById(Integer id);

    Event createEvent(EventRequest eventRequest);

    Event updateEvent(Integer id, EventRequest eventRequest);

    void deleteById(Integer id);
}
