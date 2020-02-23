package com.travix.toughjet.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travix.toughjet.entity.Flight;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

	@Autowired
	private EntityManagerFactory emf;

	@Override
	public List<Flight> findFlightBy(String from, String to, LocalDate outboundDate, int numberOfAdults) {

		EntityManager em = emf.createEntityManager();

		TypedQuery<Flight> query = em.createQuery("SELECT F FROM Flight F "
				+ "where departureAirport=:departureAirport and arrivalAirport=:arrivalAirport "
				+ "and outboundDateTime>=:startOutboundDatetime and outboundDateTime<:endOutboundDatetime and capacity>=:numberOfAdults ",
				Flight.class);

		List<Flight> flights = query.setParameter("departureAirport", from).setParameter("arrivalAirport", to)
				.setParameter("startOutboundDatetime", outboundDate.atStartOfDay())
				.setParameter("endOutboundDatetime", outboundDate.plusDays(1).atStartOfDay())
				.setParameter("numberOfAdults", numberOfAdults).getResultList();

		em.getTransaction().commit();
		em.close();

		return flights;
	}
}