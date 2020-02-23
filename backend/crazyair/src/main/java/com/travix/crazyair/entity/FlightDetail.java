package com.travix.crazyair.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "crz_flight_detail")
public class FlightDetail implements Serializable {
	/** * */
	private static final long serialVersionUID = -7897080198535980571L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String status;

	@NotBlank
	private Long flightId;

	@NotBlank
	private String cabinClass;

	@NotBlank
	private int capacity;

	@NotBlank
	private double price;
}
