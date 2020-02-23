package com.travix.medusa.busyflights.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.adapter.CrazyAirFlightSupplierAdapter;
import com.travix.medusa.busyflights.service.external.CrazyAirFlightSupplier;

@RunWith(SpringRunner.class)
public class FlightServiceIntegrationTest {

	@TestConfiguration
	static class FlightFinderServiceIntegrationTestContextConfiguration {

		@Bean
		public FlightFinderService flightService() {
			return new FlightFinderService();
		}

		@Bean
		public FlightSupplier supplier() {
			return new CrazyAirFlightSupplierAdapter(new CrazyAirFlightSupplier());
		}
	}

	@Autowired
	private FlightSupplier supplier;

	@Before
	public void setUp() {
		BusyFlightsRequest request = new BusyFlightsRequest("SAW", "AMS", LocalDate.of(2020, 03, 01),
				LocalDate.of(2020, 03, 04), 3);

		BusyFlightsResponse response = new BusyFlightsResponse("Pegasus", "CrazyAir", 65.7, "SAW", "AMS",
				LocalDateTime.now().toString(), LocalDateTime.now().plusHours(3).toString());
		List<BusyFlightsResponse> sampleResult = new ArrayList<>();
		sampleResult.add(response);

		Mockito.when(supplier.findFlight(request)).thenReturn(sampleResult);

	}

	@Test
	public void whenValidFindFlight_thenFlightShouldBeFound() {
		BusyFlightsRequest dto = new BusyFlightsRequest("SAW", "AMS", LocalDate.of(2020, 03, 01),
				LocalDate.of(2020, 03, 04), 3);

		List<BusyFlightsResponse> result = supplier.findFlight(dto);
		assertNotNull(result);

	}
}