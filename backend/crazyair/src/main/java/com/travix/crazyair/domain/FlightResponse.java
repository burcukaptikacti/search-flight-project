package com.travix.crazyair.domain;

import java.time.LocalDateTime;

public class FlightResponse {

private String airline;

private double price;

private String cabinClass;

private String departureAirportCode;

private String destinationAirportCode;

private LocalDateTime departureDate;

private LocalDateTime arrivalDate;

public FlightResponse() {
	super();
}

public String getAirline() {
	return airline;
}

public void setAirlineName(String airline) {
	this.airline = airline;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getCabinClass() {
	return cabinClass;
}

public void setCabinClass(String cabinClass) {
	this.cabinClass = cabinClass;
}

public String getDepartureAirportCode() {
	return departureAirportCode;
}

public void setDepartureAirportCode(String departureAirportCode) {
	this.departureAirportCode = departureAirportCode;
}

public String getDestinationAirportCode() {
	return destinationAirportCode;
}

public void setDestinationAirportCode(String destinationAirportCode) {
	this.destinationAirportCode = destinationAirportCode;
}

public LocalDateTime getDepartureDate() {
	return departureDate;
}

public void setDepartureDate(LocalDateTime departureDate) {
	this.departureDate = departureDate;
}

public LocalDateTime getArrivalDate() {
	return arrivalDate;
}

public void setArrivalDate(LocalDateTime arrivalDate) {
	this.arrivalDate = arrivalDate;
}
}