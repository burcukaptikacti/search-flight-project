package com.travix.crazyair.domain;

import java.time.LocalDate;

public class FlightRequest { private String origin; private String destination; private LocalDate departureDate; private LocalDate returnDate; private int passengerCount;

public FlightRequest(String origin, String destination, LocalDate departureDate, LocalDate returnDate,
		int passengerCount) {
	super();
	this.origin = origin;
	this.destination = destination;
	this.departureDate = departureDate;
	this.returnDate = returnDate;
	this.passengerCount = passengerCount;
}

public String getOrigin() {
	return origin;
}

public void setOrigin(String origin) {
	this.origin = origin;
}

public String getDestination() {
	return destination;
}

public void setDeparture(String destination) {
	this.destination = destination;
}

public LocalDate getDepartureDate() {
	return departureDate;
}

public void setDepartureDate(LocalDate departureDate) {
	this.departureDate = departureDate;
}

public LocalDate getReturnDate() {
	return returnDate;
}

public void setReturnDate(LocalDate returnDate) {
	this.returnDate = returnDate;
}

public int getPassengerCount() {
	return passengerCount;
}

public void setPassengerCount(int passengerCount) {
	this.passengerCount = passengerCount;
}
}