package com.nagarro.javaAdvance.assignment4.controller;

import com.nagarro.javaAdvance.assignment4.model.Airline;
import com.nagarro.javaAdvance.assignment4.model.Flight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ReadAirlineFromFile {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static Airline readFile(File file) {
        BufferedReader reader = null;
        Airline airline = new Airline();
        airline.setName(file.getName());
        HashSet<Flight> flight_Set = new HashSet<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            line = reader.readLine();

            while (line != null) {
                Flight f = manipulateLine(line, airline);
                line = reader.readLine();
                flight_Set.add(f);
            }
        } catch (Exception e) {
            System.err.println("Could Not Read the File");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    System.err.println("Could Not Close the File");
                }
            }
        }
        airline.setFlights(flight_Set);
        return airline;
    }

    private static Flight manipulateLine(String line, Airline aObj) {

        StringTokenizer st = new StringTokenizer(line, "|");

        String flightNo = st.nextToken();
        String depLoc = st.nextToken();
        String arrLoc = st.nextToken();

        String validTillDate = st.nextToken();
        Date validTill = new Date();
        try {
            validTill = dateFormat.parse(validTillDate);
        } catch (ParseException e) {
            System.err.print("Date not in appropriate(dd-MM-yyyy) format");
        }

        String flightTime = st.nextToken();
        double flightDuration = Double.parseDouble(st.nextToken());
        int fare = Integer.parseInt(st.nextToken());

        String avail = st.nextToken();
        boolean seatAvailability;
        seatAvailability = avail.charAt(0) == 'Y';

        String flightClass = st.nextToken();

        return new Flight(flightNo, depLoc, arrLoc, fare, validTill,
                flightTime, flightDuration, seatAvailability, flightClass, aObj);
    }
}
