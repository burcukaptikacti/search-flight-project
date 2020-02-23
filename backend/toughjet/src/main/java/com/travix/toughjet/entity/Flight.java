package com.travix.toughjet.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tjet_flight")
public class Flight implements Serializable {
	/** * */
	private static final long serialVersionUID = -7687508731472260974L;

	public Flight() {
		super();
	}

	public Flight(Long id, @NotBlank int status, @NotBlank String airlineName, @NotBlank String departureAirport,
			@NotBlank String arrivalAirport, @NotBlank Instant outboundDatetime, @NotBlank Instant inboundDatetime,
			@NotBlank double basePrice, @NotBlank double tax, @NotBlank double discount, @NotBlank int capacity) {
		super();
		this.id = id;
		this.status = status;
		this.airlineName = airlineName;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.outboundDatetime = outboundDatetime;
		this.inboundDatetime = inboundDatetime;
		this.basePrice = basePrice;
		this.tax = tax;
		this.discount = discount;
		this.capacity = capacity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private int status;

	@NotBlank
	private String airlineName;

	@NotBlank
	private String departureAirport;

	@NotBlank
	private String arrivalAirport;

	@NotBlank
	private Instant outboundDatetime;

	@NotBlank
	@Column(name = "INBOUNDATETIME")
	private Instant inboundDatetime;

	@NotBlank
	private double basePrice;

	@NotBlank
	private double tax;

	@NotBlank
	private double discount;

	@NotBlank
	private int capacity;

	public String getDepartureAirportName() {
		return departureAirport;
	}

	public String getArrivalAirportName() {
		return arrivalAirport;
	}

	public Instant getOutboundDateTime() {
		return outboundDatetime;
	}

	public Instant getInboundDateTime() {
		return inboundDatetime;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public double getTax() {
		return tax;
	}

	public double getDiscount() {
		return discount;
	}

	@JsonProperty("carrier")
	public String getAirlineName() {
		return airlineName;
	}
}