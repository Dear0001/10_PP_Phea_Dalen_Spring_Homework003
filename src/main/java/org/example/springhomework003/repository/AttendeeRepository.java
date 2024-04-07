package org.example.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.example.springhomework003.model.Attendee;
import org.example.springhomework003.model.dto.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Select("""
    SELECT * FROM attendees ORDER BY id
    LIMIT #{numberSize}
    OFFSET #{numberSize} * (#{numberPage} -1)
    """)
    @Results(id = "attendeeResultMap", value = {
            @Result(property = "attendeeId", column = "id"),
            @Result(property = "attendeeName", column = "attendees_name"),
            @Result(property = "attendeeEmail", column = "email")
    })
    List<Attendee> getAllAttendees(int numberPage, int numberSize);

    @Select("""
    SELECT * FROM attendees
    WHERE id = #{id}
    """)
    @ResultMap("attendeeResultMap")
    Attendee getAttendeeById(Integer id);
    @Select("""
    INSERT INTO attendees (attendees_name, email)
    VALUES (#{attendeeRequest.attendeeName}, #{attendeeRequest.attendeeEmail})
    RETURNING *
    """)
    @ResultMap("attendeeResultMap")
    Attendee postAttendee(@Param("attendeeRequest") AttendeeRequest attendeeRequest);

    @Select("""
    UPDATE attendees
    SET attendees_name = #{attendee.attendeeName}, email = #{attendee.attendeeEmail}
    WHERE id = #{id}
    RETURNING *
    """)
    @ResultMap("attendeeResultMap")
    Attendee updateAttendee(@Param("id") Integer id, @Param("attendee") AttendeeRequest attendeeRequest);

    @Delete("""
    DELETE FROM attendees
    WHERE id = #{id}
    """)
    @ResultMap("attendeeResultMap")
    void deleteAttendee(Integer id);

   @Select("""
        SELECT a.id, a.attendees_name, a.email, e.event_name, e.event_date
        FROM attendees a
        JOIN attendees_events ae ON a.id = ae.attendee_id
        JOIN events e ON ae.event_id = e.id
        WHERE ae.event_id = #{id}
    """)
   @ResultMap("attendeeResultMap")
   List<Attendee> getAllAttendList(Integer id);

    @Select("SELECT COUNT(*) FROM attendees WHERE id = #{attendeeId}")
    int countAttendeeById(@Param("attendeeId") Integer attendeeId);
}