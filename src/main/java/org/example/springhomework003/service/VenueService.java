package org.example.springhomework003.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.springhomework003.model.Venue;
import org.example.springhomework003.model.dto.request.VenueRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VenueService {
    List<Venue> getAllVenues(int numberPage, int numberSize);

    Venue getVenueById(Integer id);

    Venue postVenue(VenueRequest venueRequest);

    Venue updateVenue(Integer id, VenueRequest venueRequest);

    void deleteVenue(Integer id);
}
