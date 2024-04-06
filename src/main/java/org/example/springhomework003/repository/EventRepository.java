package org.example.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.example.springhomework003.model.Event;
import org.example.springhomework003.model.dto.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {
    @Select("""
    SELECT * FROM events LIMIT #{numberSize} OFFSET #{numberSize} * (#{numberPage} - 1)
    """)
    @Result(property = "venue", column = "venue_id", one = @One(select = "org.example.springhomework003.repository.VenueRepository.getVenueById"))
    @Result(property = "eventId", column = "id")
    @Result(property = "eventName", column = "event_name")
    @Result(property = "eventDateTime", column = "event_date")
    @Result(property = "attendees", column = "id", many = @Many(select = "org.example.springhomework003.repository.AttendeeRepository.getAllAttendList"))
    List<Event> getAllEvents(int numberPage, int numberSize);

    @Select("""
    SELECT * FROM events WHERE id = #{id}
    """)
    @Result(property = "venue", column = "venue_id", one = @One(select = "org.example.springhomework003.repository.VenueRepository.getVenueById"))
    @Result(property = "eventId", column = "id")
    @Result(property = "eventName", column = "event_name")
    @Result(property = "eventDateTime", column = "event_date")
    @Result(property = "attendees", column = "id", many = @Many(select = "org.example.springhomework003.repository.AttendeeRepository.getAllAttendList"))
    Event getEventById(Integer id);

    @Select("""
    INSERT INTO events (event_name, event_date, venue_id)
    VALUES (#{eventRequest.eventName}, #{eventRequest.eventDateTime}, #{eventRequest.venueId})
    RETURNING *
    """)
    @Result(property = "venue", column = "venue_id", one = @One(select = "org.example.springhomework003.repository.VenueRepository.getVenueById"))
    @Result(property = "eventId", column = "id")
    @Result(property = "eventName", column = "event_name")
    @Result(property = "eventDateTime", column = "event_date")
    @Result(property = "attendees", column = "id", many = @Many(select = "org.example.springhomework003.repository.AttendeeRepository.getAllAttendList"))
    @Result(property = "attendeeName", column = "attendees_name")
    @Result(property = "attendeeEmail", column = "email")
    Event postEvent(@Param("eventRequest") EventRequest eventRequest);


    @Insert("""
        INSERT INTO attendees_events (attendee_id, event_id) VALUES (#{attendeesId}, #{eventId})
        """)
    void attendeeEvent(Integer eventId, Integer attendeesId);


//    @Select("""
//    UPDATE events SET event_name = #{eventName}, event_date = #{eventDAteTime}, venue_id = #{venueId}
//    WHERE id = #{eventId.id}
//    """)
//    @Result(property = "venue", column = "venue_id", one = @One(select = "org.example.springhomework003.repository.VenueRepository.getVenueById"))
//    @Result(property = "eventId", column = "id")
//    @Result(property = "eventName", column = "event_name")
//    @Result(property = "eventDateTime", column = "event_date")
//    @Result(property = "attendees", column = "id", many = @Many(select = "org.example.springhomework003.repository.AttendeeRepository.getAllAttendList"))
//    @Result(property = "attendeeName", column = "attendees_name")
//    @Result(property = "attendeeEmail", column = "email")
//    Event updateEvent(@Param("eventId") Integer id, EventRequest eventRequest);
//
//    @Update("""
//    UPDATE attendees_events
//    SET event_id = #{eventId}
//    WHERE attendee_id = #{attendeeId}
//    """)
//    void updateEventAttendee(Integer eventId, Integer attendeeId);

    @Select("""
        UPDATE events
        SET event_name = #{eventRequest.eventName}, event_date = #{eventRequest.eventDateTime}, venue_id = #{eventRequest.venueId}
        WHERE id = #{eventId}
        RETURNING *
        """)
    @Result(property = "venue", column = "venue_id", one = @One(select = "org.example.springhomework003.repository.VenueRepository.getVenueById"))
    @Result(property = "eventId", column = "id")
    @Result(property = "eventName", column = "event_name")
    @Result(property = "eventDateTime", column = "event_date")
    @Result(property = "attendees", column = "id", many = @Many(select = "org.example.springhomework003.repository.AttendeeRepository.getAllAttendList"))
    @Result(property = "attendeeName", column = "attendees_name")
    @Result(property = "attendeeEmail", column = "email")
    Event updateEvent(@Param("eventId") Integer id, @Param("eventRequest") EventRequest eventRequest);

    @Delete("""
    DELETE FROM attendees_events
    WHERE event_id = #{eventId}
""")
    void removeAllEventAttendees(@Param("eventId") Integer eventId);

    @Delete("""
    DELETE FROM events
    WHERE id = #{id}
    """)
    void deleteEvent(Integer id);
}
