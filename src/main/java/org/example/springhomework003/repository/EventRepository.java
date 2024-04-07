package org.example.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.example.springhomework003.model.Event;
import org.example.springhomework003.model.dto.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {
    @Select("""
    SELECT *
    FROM events ORDER BY id
    LIMIT #{numberSize}
    OFFSET #{numberSize} * (#{numberPage} - 1)
    """)
    @Results(id = "eventResultMap", value = {
            @Result(property = "eventId", column = "id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDateTime", column = "event_date"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "org.example.springhomework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "id", many = @Many(select = "org.example.springhomework003.repository.AttendeeRepository.getAllAttendList"))
    })
    List<Event> getAllEvents(int numberPage, int numberSize);

    @Select("""
    SELECT * FROM events WHERE id = #{id}
    """)
    @ResultMap("eventResultMap")
    Event getEventById(Integer id);

    @Select("""
        INSERT INTO events (event_name, event_date, venue_id)
        VALUES (#{eventRequest.eventName}, #{eventRequest.eventDateTime}, #{eventRequest.venueId})
        RETURNING *
        """)
    @ResultMap("eventResultMap")
    Event postEvent(@Param("eventRequest") EventRequest eventRequest);

    @Insert("""
        INSERT INTO attendees_events (attendee_id, event_id)
        VALUES (#{attendeesId}, #{eventId})
        """)
    void attendeeEvent(Integer eventId, Integer attendeesId);

    @Select("""
        UPDATE events
        SET event_name = #{eventRequest.eventName}, event_date = #{eventRequest.eventDateTime}, venue_id = #{eventRequest.venueId}
        WHERE id = #{eventId}
        RETURNING *
        """)
    @ResultMap("eventResultMap")
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
