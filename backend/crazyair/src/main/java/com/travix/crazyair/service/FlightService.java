package com.travix.crazyair.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travix.crazyair.domain.FlightRequest;
import com.travix.crazyair.domain.FlightResponse;
import com.travix.crazyair.repository.FlightRepository;

@Service
public class FlightService {

	@Autowired
	private FlightRepository repository;

	public List<FlightResponse> findFlight(FlightRequest request) {

		validateRequest(request);

		List<FlightResponse> departureFlights = getDepatureFlights(request);

		List<FlightResponse> returnFlights = getReturnFlights(request);

		departureFlights.addAll(returnFlights);
		return departureFlights;
	}

	private List<FlightResponse> getDepatureFlights(FlightRequest request) {
		List<Object[]> flights = repository.findFlightBy(request.getOrigin(), request.getDestination(),
				request.getDepartureDate(), request.getPassengerCount());

		return convertObjectArrayToFlightList(flights);
	}

	private List<FlightResponse> getReturnFlights(FlightRequest request) {
		List<Object[]> flights = repository.findFlightBy(request.getDestination(), request.getOrigin(),
				request.getReturnDate(), request.getPassengerCount());

		return convertObjectArrayToFlightList(flights);
	}

	private List<FlightResponse> convertObjectArrayToFlightList(List<Object[]> objects) {
		List<FlightResponse> foundedFlights = new ArrayList<>();

		for (Object[] obj : objects) {
			FlightResponse convertedFlight = convertObjectArrayToFlight(obj);
			foundedFlights.add(convertedFlight);
		}

		return foundedFlights;
	}

	private FlightResponse convertObjectArrayToFlight(Object[] obj) {
		FlightResponse flight = new FlightResponse();
		flight.setAirlineName((String) obj[0]);
		flight.setDepartureAirportCode((String) obj[1]);
		flight.setDestinationAirportCode((String) obj[2]);
		flight.setDepartureDate((LocalDateTime) obj[3]);
		flight.setArrivalDate((LocalDateTime) obj[4]);
		flight.setCabinClass((String) obj[5]);
		flight.setPrice((double) obj[6]);

		return flight;
	}

	private void validateRequest(FlightRequest request) {
		if (request.getDestination() == null || request.getDestination().isEmpty())
			throw new IllegalArgumentException("Destination cannot be empty!");

		if (request.getOrigin() == null || request.getOrigin().isEmpty())
			throw new IllegalArgumentException("Origin cannot be empty!");
		
		if (request.getDestination().equalsIgnoreCase(request.getOrigin()))
			throw new IllegalArgumentException("Origin and destination cannot be same!");

		if (request.getPassengerCount() < 1)
			throw new IllegalArgumentException("Passenger count cannot be lower than one!");

		if (LocalDate.now().compareTo(request.getDepartureDate()) > 0)
			throw new IllegalArgumentException("Departure date should be greater than now");

		if (LocalDate.now().compareTo(request.getReturnDate()) > 0)
			throw new IllegalArgumentException("Return date should be greater than today");

		if (request.getDepartureDate().compareTo(request.getReturnDate()) > 0)
			throw new IllegalArgumentException("Return date should be greater than departure date");
	}

}
