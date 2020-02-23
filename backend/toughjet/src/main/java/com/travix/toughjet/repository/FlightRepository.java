package com.travix.toughjet.repository;

import java.time.LocalDate;
import java.util.List;

import com.travix.toughjet.entity.Flight;

public interface FlightRepository {
	List<Flight> findFlightBy(String from, String to, LocalDate outboundDate, int numberOfAdults);
}