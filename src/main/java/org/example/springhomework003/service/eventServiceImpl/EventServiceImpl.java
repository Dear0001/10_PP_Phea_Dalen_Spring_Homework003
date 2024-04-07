package org.example.springhomework003.service.eventServiceImpl;

import org.example.springhomework003.exception.AllNotFoundException;
import org.example.springhomework003.model.Event;
import org.example.springhomework003.model.dto.request.EventRequest;
import org.example.springhomework003.repository.AttendeeRepository;
import org.example.springhomework003.repository.EventRepository;
import org.example.springhomework003.repository.VenueRepository;
import org.example.springhomework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final AttendeeRepository attendeeRepository;

    public EventServiceImpl(EventRepository eventRepository, VenueRepository venueRepository, AttendeeRepository attendeeRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Event> getAllEvents(int numberPage, int numberSize) {
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
        handlerNotFoundAttendeeAndVenue(eventRequest);
        Event event = eventRepository.postEvent(eventRequest);

        Integer eventId = event.getEventId();

        for(Integer attendeeId : eventRequest.getAttendeeId()){
            eventRepository.attendeeEvent(eventId, attendeeId);
        }

        return getEventById(eventId);
    }

    @Override
    public Event updateEvent(Integer id, EventRequest eventRequest) {
        handlerNotFoundAttendeeAndVenue(eventRequest);
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
    //
    private void handlerNotFoundAttendeeAndVenue(EventRequest eventRequest) {
        if (venueRepository.countVenueById(eventRequest.getVenueId()) == 0) {
            throw new AllNotFoundException("Venue with id " + eventRequest.getVenueId() + " does not exist.");
        }

        for (Integer attendeeId : eventRequest.getAttendeeId()) {
            if (attendeeRepository.countAttendeeById(attendeeId) == 0) {
                throw new AllNotFoundException("Attendee with id " + attendeeId + " does not exist.");
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        if(eventRepository.getEventById(id) == null){
            throw new AllNotFoundException("Event with id " + id + " does not found.");
        }
        eventRepository.removeAllEventAttendees(id);
        eventRepository.deleteEvent(id);
    }
}
