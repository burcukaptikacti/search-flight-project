package com.travix.medusa.busyflights.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.factory.FlightSupplierFactory;
import com.travix.medusa.busyflights.util.FlightSupplierTypes;

@Service
public class FlightFinderService {

	private Logger logger = LoggerFactory.getLogger(FlightFinderService.class);

	public List<BusyFlightsResponse> findFlight(BusyFlightsRequest request) {
		validateRequest(request);

		List<CompletableFuture<List<BusyFlightsResponse>>> futures = new ArrayList<CompletableFuture<List<BusyFlightsResponse>>>();

		for (FlightSupplierTypes supplierType : FlightSupplierTypes.values()) {
			try {
				FlightSupplier supplier = FlightSupplierFactory.getSupplier(supplierType);
				futures.add(findFlights(supplier, request));
			} catch (InterruptedException e) {
				logger.error("Error has occured during findFlight method.");
				logger.error(e.getMessage());
			}
		}

		return combineFligthsofSuppliers(futures);
	}

	private void validateRequest(BusyFlightsRequest request) {
		if (request.getDestination() == null || request.getDestination().isEmpty())
			throw new IllegalArgumentException("Destination cannot be empty!");

		if (request.getOrigin() == null || request.getOrigin().isEmpty())
			throw new IllegalArgumentException("Origin cannot be empty!");

		if (request.getNumberOfPassengers() < 1)
			throw new IllegalArgumentException("Number of passengers cannot be lower than one!");

		if (LocalDate.now().compareTo(request.getDepartureDate()) > 0)
			throw new IllegalArgumentException("Departure date should be greater than now");

		if (LocalDate.now().compareTo(request.getReturnDate()) > 0)
			throw new IllegalArgumentException("Return date should be greater than today");

		if (request.getDepartureDate().compareTo(request.getReturnDate()) > 0)
			throw new IllegalArgumentException("Return date should be greater than departure date");

	}

	private List<BusyFlightsResponse> combineFligthsofSuppliers(
			List<CompletableFuture<List<BusyFlightsResponse>>> futures) {
		List<BusyFlightsResponse> allFlights = new ArrayList<>();

		for (CompletableFuture<List<BusyFlightsResponse>> future : futures) {

			try {
				List<BusyFlightsResponse> foundedFlights = future.get();
				allFlights.addAll(foundedFlights);
			} catch (InterruptedException | ExecutionException e) {
				logger.error("Error has occured during combineFligthsofSuppliers method.");
				logger.error(e.getMessage());
			}
		}
		Collections.sort(allFlights, Comparator.comparing(BusyFlightsResponse::getFare));
		return allFlights;
	}

	@Async("taskExecutor")
	public CompletableFuture<List<BusyFlightsResponse>> findFlights(FlightSupplier supplier, BusyFlightsRequest request)
			throws InterruptedException {
		List<BusyFlightsResponse> flights = supplier.findFlight(request);
		return CompletableFuture.completedFuture(flights);
	}
}