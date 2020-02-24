package com.travix.toughjet.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travix.toughjet.domain.FlightRequest;
import com.travix.toughjet.entity.Flight;
import com.travix.toughjet.repository.FlightRepository;

@Service
public class FlightService {

	@Autowired
	private FlightRepository repository;

	public List<Flight> findFlight(FlightRequest request) {
		validateRequest(request);

		List<Flight> departureFlights = getDepatureFlights(request);

		List<Flight> returnFlights = getReturnFlights(request);

		departureFlights.addAll(returnFlights);
		return departureFlights;
	}

	private List<Flight> getDepatureFlights(FlightRequest request) {
		List<Flight> flights = repository.findFlightBy(request.getFrom(), request.getTo(), request.getOutboundDate(),
				request.getNumberOfAdults());

		return flights;

	}

	private List<Flight> getReturnFlights(FlightRequest request) {
		List<Flight> flights = repository.findFlightBy(request.getTo(), request.getFrom(), request.getInboundDate(),
				request.getNumberOfAdults());

		return flights;
	}

	private void validateRequest(FlightRequest request) {
		if (request.getFrom() == null || request.getFrom().isEmpty())
			throw new IllegalArgumentException("From cannot be empty!");

		if (request.getTo() == null || request.getTo().isEmpty())
			throw new IllegalArgumentException("To cannot be empty!");
		
		if (request.getFrom().equalsIgnoreCase(request.getTo()))
			throw new IllegalArgumentException("Origin and destination cannot be same!");

		if (request.getNumberOfAdults() < 1)
			throw new IllegalArgumentException("Passenger count cannot be lower than one!");

		if (LocalDate.now().compareTo(request.getOutboundDate()) > 0)
			throw new IllegalArgumentException("Outbound date should be greater than now");

		if (LocalDate.now().compareTo(request.getInboundDate()) > 0)
			throw new IllegalArgumentException("Inbound date should be greater than today");

		if (request.getOutboundDate().compareTo(request.getInboundDate()) > 0)
			throw new IllegalArgumentException("Inbound date should be greater than outbound date");
	}
}
