package org.example.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.example.springhomework003.model.Venue;
import org.example.springhomework003.model.dto.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Select("""
    SELECT * FROM venues
    LIMIT #{numberSize} OFFSET #{numberSize} * (#{numberPage} -1)
    """)
    @Result(property = "venueId", column = "venue_id")
    @Result(property = "venueName", column = "venue_name")
    @Result(property = "venueLocation", column = "venue_location")
    List<Venue> getAllVenues(int numberPage, int numberSize);


    @Select("""
SELECT * FROM venues
    WHERE venue_id = #{id}
     """)
    @Result(property = "venueId", column = "venue_id")
    @Result(property = "venueName", column = "venue_name")
    @Result(property = "venueLocation", column = "venue_location")
    Venue getVenueById(Integer id);

    @Select("""
    INSERT INTO venues (venue_name, venue_location) 
    VALUES (#{venueRequest.venueName}, #{venueRequest.venueLocation})
    RETURNING *
    """)
    @Result(property = "venueId", column = "venue_id")
    @Result(property = "venueName", column = "venue_name")
    @Result(property = "venueLocation", column = "venue_location")
    Venue postVenue(@Param("venueRequest") VenueRequest venueRequest);


    @Select("""
        UPDATE venues SET venue_name = #{venueRequest.venueName}, venue_location = #{venueRequest.venueLocation}
        WHERE venue_id = #{venueId}
        RETURNING *
        """)
        @Result(property = "venueId", column = "venue_id")
        @Result(property = "venueName", column = "venue_name")
        @Result(property = "venueLocation", column = "venue_location")
        Venue updateVenue(@Param("venueId") Integer id, @Param("venueRequest") VenueRequest venueRequest);

    @Delete("""
    DELETE From venues
    WHERE venue_id = #{id}
    """)
    void deleteVenue(Integer id);
}
