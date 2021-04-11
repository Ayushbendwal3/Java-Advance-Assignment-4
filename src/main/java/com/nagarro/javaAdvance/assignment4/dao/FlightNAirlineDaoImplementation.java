package com.nagarro.javaAdvance.assignment4.dao;

import com.nagarro.javaAdvance.assignment4.model.Airline;
import com.nagarro.javaAdvance.assignment4.model.Flight;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class FlightNAirlineDaoImplementation implements FlightNAirlineDao {

    HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public void saveAirline(Airline airline) {
        Session session = template.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(airline);
        session.getTransaction().commit();
        session.close();
    }

    public List<Flight> getFlights() {
        return template.loadAll(Flight.class);
    }

    public void deleteAirline(String airlineName) {
        Session session = template.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        TypedQuery<Airline> query = session.createQuery("from Airline where name = :string");
        query.setParameter("string", airlineName);
        try {
            Airline airline = query.getSingleResult();
            airline = session.load(Airline.class, airline.getId());
            session.delete(airline);
            session.getTransaction().commit();
            session.close();
        } catch (NoResultException e) {
            System.err.println("No Airline Found");
        }
    }
}
