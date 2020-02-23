package com.travix.medusa.busyflights.domain.busyflights;

import java.time.LocalDate;

public class BusyFlightsRequest {

private String origin;
private String destination;
private LocalDate departureDate;
private LocalDate returnDate;
private int numberOfPassengers;


public BusyFlightsRequest(String origin, String destination, LocalDate departureDate, LocalDate returnDate,
		int numberOfPassengers) {
	super();
	this.origin = origin;
	this.destination = destination;
	this.departureDate = departureDate;
	this.returnDate = returnDate;
	this.numberOfPassengers = numberOfPassengers;
}

public String getOrigin() {
    return origin;
}

public void setOrigin(final String origin) {
    this.origin = origin;
}

public String getDestination() {
    return destination;
}

public void setDestination(final String destination) {
    this.destination = destination;
}

public LocalDate getDepartureDate() {
    return departureDate;
}

public void setDepartureDate(final LocalDate departureDate) {
    this.departureDate = departureDate;
}

public LocalDate getReturnDate() {
    return returnDate;
}

public void setReturnDate(final LocalDate returnDate) {
    this.returnDate = returnDate;
}

public int getNumberOfPassengers() {
    return numberOfPassengers;
}

public void setNumberOfPassengers(final int numberOfPassengers) {
    this.numberOfPassengers = numberOfPassengers;
}
}

