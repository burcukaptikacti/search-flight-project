package com.travix.crazyair.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

	@Autowired
	private EntityManagerFactory emf;

	@Override
	public List<Object[]> findFlightBy(String origin, String destination, LocalDate departureDate, int passengerCount) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Object[]> foundedFlights = em.createQuery(
				"SELECT F.airlineName,F.departureAirport,F.destinationAirport,F.departureDatetime, F.arrivalDatetime,D.cabinClass,D.price FROM Flight F , FlightDetail D "
						+ "where F.id=D.flightId and F.departureAirport=:departureAirport and F.destinationAirport=:destinationAirport "
						+ "and F.departureDatetime>=:startDepartureDatetime and F.departureDatetime<:endDepartureDatetime and D.capacity>=:passengerCount",
				Object[].class).setParameter("departureAirport", origin).setParameter("destinationAirport", destination)
				.setParameter("startDepartureDatetime", departureDate.atStartOfDay())
				.setParameter("endDepartureDatetime", departureDate.plusDays(1).atStartOfDay())
				.setParameter("passengerCount", passengerCount).getResultList();

		em.getTransaction().commit();
		em.close();

		return foundedFlights;
	}
}