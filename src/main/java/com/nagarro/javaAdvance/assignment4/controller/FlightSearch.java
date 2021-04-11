package com.nagarro.javaAdvance.assignment4.controller;

import com.nagarro.javaAdvance.assignment4.model.Flight;
import com.nagarro.javaAdvance.assignment4.model.FlightDetailsEntered;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FlightSearch {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, "flightDate", new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/flightSearch", method = RequestMethod.GET)
    public ModelAndView getFlightSearch() {
        return new ModelAndView("flightSearch");
    }

    @RequestMapping(value = "/flightSearch", method = RequestMethod.POST)
    public ModelAndView flightSearch(
            @Valid @ModelAttribute("flightDetails") FlightDetailsEntered flightDetails, BindingResult result) {

        ModelAndView modelAndView = new ModelAndView("flightSearch");
        if (result.hasErrors()) {
            System.err.println(result);
            return modelAndView;
        }
        List<Flight> listOfMatchingFlights = flightDetails.getListOfMatchingFlights();
        modelAndView = new ModelAndView("flightList");
        modelAndView.addObject("list", listOfMatchingFlights);
        return modelAndView;
    }
}
