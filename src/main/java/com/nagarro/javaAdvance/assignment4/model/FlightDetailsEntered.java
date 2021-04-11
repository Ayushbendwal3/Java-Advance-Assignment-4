package com.nagarro.javaAdvance.assignment4.model;

import com.nagarro.javaAdvance.assignment4.dao.FlightNAirlineDao;
import com.nagarro.javaAdvance.assignment4.util.AppContextUtil;
import com.nagarro.javaAdvance.assignment4.util.FlightDurationComparator;
import com.nagarro.javaAdvance.assignment4.util.FlightPriceComparator;

import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FlightDetailsEntered {
    @Size(min = 3, max = 3, message = "should be a 3 letter code")
    private String depLoc;

    @Size(min = 3, max = 3, message = "should be a 3 letter code")
    private String arrLoc;

    private Date flightDate;
    @NotEmpty
    private String flightClass;

    @Max(value = 2, message = "Choose valid entry")
    @Min(value = 1, message = "is required")
    private int outputPreference;

    public String getDepLoc() {
        return depLoc;
    }

    public void setDepLoc(String depLoc) {
        this.depLoc = depLoc;
    }

    public String getArrLoc() {
        return arrLoc;
    }

    public void setArrLoc(String arrLoc) {
        this.arrLoc = arrLoc;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public int getOutputPreference() {
        return outputPreference;
    }

    public void setOutputPreference(int outputPreference) {
        this.outputPreference = outputPreference;
    }

    public List<Flight> getListOfMatchingFlights() {
        FlightNAirlineDao flightDao = (FlightNAirlineDao) AppContextUtil.context.getBean("flightDao");
        List<Flight> allFlights = flightDao.getFlights();
        ArrayList<Flight> matchingFlights = new ArrayList<>();


        for (Flight flight : allFlights) {
            if (flight.getDepLoc().equalsIgnoreCase(getDepLoc())
                    && flight.getArrLoc().equalsIgnoreCase(getArrLoc())
                    && flight.getFlightClass().equalsIgnoreCase(getFlightClass())
                    && (getFlightDate().compareTo(flight.getValidTill()) <= 0)
                    && flight.isSeatAvailability())
                matchingFlights.add(flight);
        }

        if (getOutputPreference() == 1)
            Collections.sort(matchingFlights, new FlightPriceComparator());
        else
            Collections.sort(matchingFlights, new FlightDurationComparator());

        return matchingFlights;
    }
}
