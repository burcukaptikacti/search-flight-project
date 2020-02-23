package com.travix.crazyair.controller;

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

import com.travix.crazyair.domain.FlightResponse;
import com.travix.crazyair.domain.FlightRequest;
import com.travix.crazyair.service.FlightService;

@RestController
@Validated
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/flights")
	public ResponseEntity<List<FlightResponse>> findFlight(@RequestParam("origin") String origin,
			@RequestParam("destination") String destination,
			@RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
			@RequestParam("returnDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,
			@RequestParam("passengerCount") int passengerCount) {
		return ResponseEntity.status(HttpStatus.OK).body(flightService
				.findFlight(new FlightRequest(origin, destination, departureDate, returnDate, passengerCount)));
	}
}