package org.example.springhomework003.service.eventServiceImpl;

import org.apache.ibatis.annotations.Param;
import org.example.springhomework003.exception.AllNotFoundException;
import org.example.springhomework003.model.Event;
import org.example.springhomework003.model.dto.request.EventRequest;
import org.example.springhomework003.repository.EventRepository;
import org.example.springhomework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents(int numberPage, int numberSize) {
        System.out.println("Hello"+ eventRepository.getAllEvents(numberPage, numberSize));
        return eventRepository.getAllEvents(numberPage, numberSize);
    }

    @Override
    public Event getEventById(Integer id) {
        Event event = eventRepository.getEventById(id);
        if(event == null){
            throw new AllNotFoundException("Event with id " + id + " does not exist.");
        }
        return event;
    }

    @Override
    public Event createEvent(EventRequest eventRequest) {
        Integer eventId = eventRepository.postEvent(eventRequest).getEventId();
        for(Integer attendeeId : eventRequest.getAttendeeId()){
            eventRepository.attendeeEvent(eventId, attendeeId);
        }
        return getEventById(eventId);
    }

    @Override
    public Event updateEvent(Integer id, EventRequest eventRequest) {
        Event event = eventRepository.updateEvent(id, eventRequest);
        if(event == null){
            throw new AllNotFoundException("Event with id " + id + " does not exist.");
        }

        eventRepository.removeAllEventAttendees(id);

        for(Integer attendeeId : eventRequest.getAttendeeId()){
            eventRepository.attendeeEvent(id, attendeeId);
        }

        return getEventById(id);
    }

    @Override
    public void deleteById(Integer id) {
        Event event = getEventById(id);
        if(event == null){
            throw new AllNotFoundException("Event with id " + id + " does not exist.");
        }
        eventRepository.removeAllEventAttendees(id);
        eventRepository.deleteEvent(id);
    }
}
