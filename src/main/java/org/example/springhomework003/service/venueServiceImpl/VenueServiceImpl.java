package org.example.springhomework003.service.venueServiceImpl;

import org.example.springhomework003.exception.AllNotFoundException;
import org.example.springhomework003.model.Venue;
import org.example.springhomework003.model.dto.request.VenueRequest;
import org.example.springhomework003.repository.VenueRepository;
import org.example.springhomework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }


    @Override
    public List<Venue> getAllVenues(int numberPage, int numberSize) {
        return venueRepository.getAllVenues(numberPage, numberSize);
    }

    @Override
    public Venue getVenueById(Integer id)  {
        Venue venue = venueRepository.getVenueById(id);
        if(venue == null){
            throw new AllNotFoundException("Venue with id " + id + " does not exist.");
        }
        return venue;
    }

    @Override
    public Venue postVenue(VenueRequest venueRequest) {
        return venueRepository.postVenue(venueRequest);
    }

    @Override
    public Venue updateVenue(Integer id, VenueRequest venueRequest) {
        Venue venue =  venueRepository.updateVenue(id, venueRequest);
        if(venue == null){
            throw new AllNotFoundException("Venue with id " + id + " does not exist.");
        }
        return venue;
    }

    @Override
    public void deleteVenue(Integer id) {
        if(venueRepository.getVenueById(id) == null){
            throw new AllNotFoundException("Venue with id " + id + " does not found.");
        }
        venueRepository.deleteVenue(id);
    }
}
