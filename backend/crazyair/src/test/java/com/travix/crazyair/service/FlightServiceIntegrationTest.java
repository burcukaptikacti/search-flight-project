package com.travix.crazyair.service;

import static org.junit.Assert.assertNotNull; import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate; import java.time.LocalDateTime; import java.util.ArrayList; import java.util.List;

import org.junit.Before; import org.junit.Test; import org.junit.runner.RunWith; import org.mockito.Mockito; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.TestConfiguration; import org.springframework.boot.test.mock.mockito.MockBean; import org.springframework.context.annotation.Bean; import org.springframework.test.context.junit4.SpringRunner;

import com.travix.crazyair.domain.FlightResponse; import com.travix.crazyair.domain.FlightRequest; import com.travix.crazyair.repository.FlightRepository;

@RunWith(SpringRunner.class) public class FlightServiceIntegrationTest {

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
private FlightRepository flightRepository;

@Before
public void setUp() {
	FlightRequest dto = new FlightRequest("SAW", "AMS", LocalDate.of(2020, 03, 01), LocalDate.of(2020, 03, 04),
			3);

	Object[] sampleFlight = { "SAW", "AMS", LocalDateTime.now(), LocalDateTime.now().plusHours(3), "B", 45.67 };
	List<Object[]> sampleResult = new ArrayList<>();
	sampleResult.add(sampleFlight);

	Mockito.when(flightRepository.findFlightBy(dto.getOrigin(), dto.getDestination(), dto.getDepartureDate(),
			dto.getPassengerCount())).thenReturn(sampleResult);

}

@Test
public void whenValidFindFlight_thenFlightShouldBeFound() {
	FlightRequest dto = new FlightRequest("SAW", "AMS", LocalDate.of(2020, 03, 01), LocalDate.of(2020, 03, 04),
			3);

	List<FlightResponse> result = flightService.findFlight(dto);
	assertNotNull(result);
	assertEquals(1L, result.size());
}
}