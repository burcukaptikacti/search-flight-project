package com.travix.toughjet.controller;

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

import com.travix.toughjet.domain.FlightRequest;
import com.travix.toughjet.entity.Flight;
import com.travix.toughjet.service.FlightService;

@RestController
@Validated
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/flights")
	public ResponseEntity<List<Flight>> findFlight(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("outboundDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate outboundDate,
			@RequestParam("inboundDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inboundDate,
			@RequestParam("numberOfAdults") int numberOfAdults) {
		return ResponseEntity.status(HttpStatus.OK).body(
				flightService.findFlight(new FlightRequest(from, to, outboundDate, inboundDate, numberOfAdults)));
	}
}