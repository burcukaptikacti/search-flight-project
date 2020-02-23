package com.travix.medusa.busyflights.service.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.FlightSupplier;
import com.travix.medusa.busyflights.service.external.CrazyAirFlightSupplier;

@Component
public class CrazyAirFlightSupplierAdapter implements FlightSupplier {

	private static final String supplierName = "CrazyAir";
	private CrazyAirFlightSupplier flightSupplier;

	public CrazyAirFlightSupplierAdapter(CrazyAirFlightSupplier flightSupplier) {
		this.flightSupplier = flightSupplier;
	}

	@Override
	public List<BusyFlightsResponse> findFlight(BusyFlightsRequest busyFlightsRequest) {

		CrazyAirRequest request = new CrazyAirRequest(busyFlightsRequest.getOrigin(),
				busyFlightsRequest.getDestination(), busyFlightsRequest.getDepartureDate(),
				busyFlightsRequest.getReturnDate(), busyFlightsRequest.getNumberOfPassengers());

		CrazyAirResponse[] foundedFlights = flightSupplier.findFlight(request);
		List<BusyFlightsResponse> response = convertToBusyFlightsResponse(foundedFlights);

		return response;
	}

	private List<BusyFlightsResponse> convertToBusyFlightsResponse(CrazyAirResponse[] foundedFlights) {
		List<BusyFlightsResponse> convertedFlights = new ArrayList<>();

		for (CrazyAirResponse foundedFlight : foundedFlights) {
			BusyFlightsResponse convertedFlight = new BusyFlightsResponse();
			convertedFlight.setSupplier(supplierName);
			convertedFlight.setAirline(foundedFlight.getAirline());
			convertedFlight.setFare(foundedFlight.getPrice());
			convertedFlight.setDepartureAirportCode(foundedFlight.getDepartureAirportCode());
			convertedFlight.setDestinationAirportCode(foundedFlight.getDestinationAirportCode());
			convertedFlight.setDepartureDate(foundedFlight.getDepartureDate());
			convertedFlight.setArrivalDate(foundedFlight.getArrivalDate());

			convertedFlights.add(convertedFlight);
		}
		return convertedFlights;
	}
}