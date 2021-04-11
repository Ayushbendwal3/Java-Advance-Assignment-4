package com.nagarro.javaAdvance.assignment4.dao;

import com.nagarro.javaAdvance.assignment4.model.Airline;
import com.nagarro.javaAdvance.assignment4.model.Flight;

import java.util.List;

public interface FlightNAirlineDao {
    public void saveAirline(Airline airline);

    public List<Flight> getFlights();

    public void deleteAirline(String airlineName);
}
