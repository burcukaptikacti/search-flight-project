package com.travix.crazyair.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "crz_flight")
public class Flight implements Serializable {
	/** * */
	private static final long serialVersionUID = -7687508731472260974L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String status;

	@NotBlank
	private String airlineName;

	@NotBlank
	private String departureAirport;

	@NotBlank
	private String destinationAirport;

	@NotBlank
	private LocalDateTime departureDatetime;

	@NotBlank
	private LocalDateTime arrivalDatetime;
}
