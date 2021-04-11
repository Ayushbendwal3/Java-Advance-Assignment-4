package com.nagarro.javaAdvance.assignment4.controller;

import com.nagarro.javaAdvance.assignment4.dao.FlightNAirlineDao;
import com.nagarro.javaAdvance.assignment4.model.Airline;
import com.nagarro.javaAdvance.assignment4.util.AppContextUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ModificationWatcher implements Runnable {
    static HashMap<String, Long> lastModifiedAt = new HashMap<>();
    File dir = new File("CSV/");

    static FlightNAirlineDao flightDao = (FlightNAirlineDao) AppContextUtil.context.getBean("flightDao");


    public void run() {
        File[] files = dir.listFiles();
        ArrayList<String> listOfFileNames = new ArrayList<>();
        for (File file : files) {
            if ((!(lastModifiedAt.containsKey(file.getName()))) || (file.lastModified() > lastModifiedAt.get(file.getName()))) {

                Airline airline = ReadAirlineFromFile.readFile(file);
                if (lastModifiedAt.containsKey(file.getName())) {

                    flightDao.deleteAirline(file.getName());
                }
                flightDao.saveAirline(airline);
                lastModifiedAt.put(file.getName(), file.lastModified());
            }
            listOfFileNames.add(file.getName());
        }

        Set<String> fileNamesOfPast = new HashSet<>(lastModifiedAt.keySet());
        if (fileNamesOfPast.size() == listOfFileNames.size())
            return;
        for (String string : fileNamesOfPast) {
            if (!(listOfFileNames.contains(string))) {
                flightDao.deleteAirline(string);
                lastModifiedAt.remove(string);
            }
        }
    }
}
