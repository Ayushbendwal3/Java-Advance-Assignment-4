package com.nagarro.javaAdvance.assignment4.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Airline {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
    private Set<Flight> flights;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
}
