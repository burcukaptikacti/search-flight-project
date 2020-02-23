package com.travix.toughjet.domain;

import java.time.LocalDate;

public class FlightRequest {
	private String from;
	private String to;
	private LocalDate outboundDate;
	private LocalDate inboundDate;
	private int numberOfAdults;

	public FlightRequest(String from, String to, LocalDate outboundDate, LocalDate inboundDate, int numberOfAdults) {
		this.from = from;
		this.to = to;
		this.outboundDate = outboundDate;
		this.inboundDate = inboundDate;
		this.numberOfAdults = numberOfAdults;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public LocalDate getOutboundDate() {
		return outboundDate;
	}

	public LocalDate getInboundDate() {
		return inboundDate;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}
}