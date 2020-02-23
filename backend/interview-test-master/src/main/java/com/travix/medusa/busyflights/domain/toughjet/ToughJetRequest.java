package com.travix.medusa.busyflights.domain.toughjet;

import java.time.LocalDate;

public class ToughJetRequest {

	private String from;
	private String to;
	private LocalDate outboundDate;
	private LocalDate inboundDate;
	private int numberOfAdults;

	public ToughJetRequest(String from, String to, LocalDate outboundDate, LocalDate inboundDate, int numberOfAdults) {
		super();
		this.from = from;
		this.to = to;
		this.outboundDate = outboundDate;
		this.inboundDate = inboundDate;
		this.numberOfAdults = numberOfAdults;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(final String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(final String to) {
		this.to = to;
	}

	public LocalDate getOutboundDate() {
		return outboundDate;
	}

	public void setOutboundDate(final LocalDate outboundDate) {
		this.outboundDate = outboundDate;
	}

	public LocalDate getInboundDate() {
		return inboundDate;
	}

	public void setInboundDate(final LocalDate inboundDate) {
		this.inboundDate = inboundDate;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(final int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}
}