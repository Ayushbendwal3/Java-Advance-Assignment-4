package com.nagarro.javaAdvance.assignment4.util;

import com.nagarro.javaAdvance.assignment4.model.Flight;

import java.util.Comparator;

public class FlightPriceComparator implements Comparator<Flight> {

    @Override
    public int compare(Flight a, Flight b) {
        return a.getFare() - b.getFare();
    }
}
