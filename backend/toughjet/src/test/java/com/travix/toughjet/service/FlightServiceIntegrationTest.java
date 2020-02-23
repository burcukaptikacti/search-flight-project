package com.travix.toughjet.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.travix.toughjet.domain.FlightRequest;
import com.travix.toughjet.entity.Flight;
import com.travix.toughjet.repository.FlightRepositoryImpl;

@RunWith(SpringRunner.class)
public class FlightServiceIntegrationTest {

	@TestConfiguration
	static class FlightServiceIntegrationTestContextConfiguration {

		@Bean
		public FlightService flightService() {
			return new FlightService();
		}
	}

	@Autowired
	private FlightService flightService;

	@MockBean
	private FlightRepositoryImpl flightRepository;

	@Before
	public void setUp() {
		FlightRequest request = new FlightRequest("SAW", "AMS", LocalDate.of(2020, 03, 01), LocalDate.of(2020, 03, 04),
				3);

		Flight flight = new Flight(1L, 1, "Pegasus", "SAW", "AMS", Instant.now(), Instant.now(), 34.5, 12.5, 2.7, 123);
		List<Flight> sampleResult = new ArrayList<>();
		sampleResult.add(flight);

		Mockito.when(flightRepository.findFlightBy(request.getFrom(), request.getTo(), request.getOutboundDate(),
				request.getNumberOfAdults())).thenReturn(sampleResult);

	}

	@Test
	public void whenValidFindFlight_thenFlightShouldBeFound() {
		FlightRequest request = new FlightRequest("SAW", "AMS", LocalDate.of(2020, 03, 01), LocalDate.of(2020, 03, 04),
				3);

		List<Flight> result = flightService.findFlight(request);
		assertNotNull(result);
		assertEquals(1L, result.size());
	}
}
