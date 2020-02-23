package com.travix.medusa.busyflights.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.FlightFinderService;

@RestController
@Validated
public class FlightFindController {

	@Autowired
	private FlightFinderService finderService;

	@GetMapping("/flights")
	public ResponseEntity<List<BusyFlightsResponse>> findFlight(@RequestParam("origin") String origin,
			@RequestParam("destination") String destination,
			@RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
			@RequestParam("returnDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,
			@RequestParam("passengerCount") int passengerCount) {
		return ResponseEntity.status(HttpStatus.OK).body(finderService
				.findFlight(new BusyFlightsRequest(origin, destination, departureDate, returnDate, passengerCount)));

	}
}
