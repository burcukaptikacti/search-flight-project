package com.travix.crazyair.repository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository {
	List<Object[]> findFlightBy(String origin, String destination, LocalDate departureDate, int passengerCount);
}