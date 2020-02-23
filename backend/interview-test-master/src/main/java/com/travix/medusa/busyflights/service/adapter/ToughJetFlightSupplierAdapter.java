package com.travix.medusa.busyflights.service.adapter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.FlightSupplier;
import com.travix.medusa.busyflights.service.external.ToughJetFlightSupplier;

@Component
public class ToughJetFlightSupplierAdapter implements FlightSupplier {

	private static final String supplierName = "ToughJet";
	private static final double wholePercentage = 100.00;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
	private ToughJetFlightSupplier flightSupplier;

	public ToughJetFlightSupplierAdapter(ToughJetFlightSupplier flightSupplier) {
		this.flightSupplier = flightSupplier;
	}

	@Override
	public List<BusyFlightsResponse> findFlight(BusyFlightsRequest busyFlightsRequest) {

		ToughJetRequest request = new ToughJetRequest(busyFlightsRequest.getOrigin(),
				busyFlightsRequest.getDestination(), busyFlightsRequest.getDepartureDate(),
				busyFlightsRequest.getReturnDate(), busyFlightsRequest.getNumberOfPassengers());

		ToughJetResponse[] foundedFlights = flightSupplier.findFlight(request);
		List<BusyFlightsResponse> response = convertToBusyFlightsResponse(foundedFlights);

		return response;
	}

	private List<BusyFlightsResponse> convertToBusyFlightsResponse(ToughJetResponse[] foundedFlights) {
		List<BusyFlightsResponse> convertedFlights = new ArrayList<>();

		for (ToughJetResponse foundedFlight : foundedFlights) {

			BusyFlightsResponse convertedFlight = new BusyFlightsResponse();
			convertedFlight.setSupplier(supplierName);
			convertedFlight.setAirline(foundedFlight.getCarrier());
			convertedFlight.setDestinationAirportCode(foundedFlight.getDepartureAirportName());
			convertedFlight.setDestinationAirportCode(foundedFlight.getArrivalAirportName());
			convertedFlight.setDepartureDate(getFormattedDatetime(foundedFlight.getOutboundDateTime()));
			convertedFlight.setArrivalDate(getFormattedDatetime(foundedFlight.getInboundDateTime()));

			double fare = calculateFare(foundedFlight);
			convertedFlight.setFare(fare);

			convertedFlights.add(convertedFlight);
		}
		return convertedFlights;
	}

	private String getFormattedDatetime(String unFormattedDate) {
		LocalDateTime date = LocalDateTime.ofInstant(Instant.parse(unFormattedDate), ZoneOffset.UTC);

		return date.format(formatter);
	}

	private double calculateFare(ToughJetResponse foundedFlight) {
		double priceWithTax = foundedFlight.getBasePrice() + foundedFlight.getTax();
		double priceWithTaxAndDiscount = priceWithTax
				* ((wholePercentage - foundedFlight.getDiscount()) / wholePercentage);

		return roundDoubleTwoDigit(priceWithTaxAndDiscount);

	}

	private double roundDoubleTwoDigit(double priceWithTaxAndDiscount) {
		int digitCount = 2;
		BigDecimal bd = BigDecimal.valueOf(priceWithTaxAndDiscount);
		bd = bd.setScale(digitCount, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}