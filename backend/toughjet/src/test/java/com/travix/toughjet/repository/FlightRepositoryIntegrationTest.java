package com.travix.toughjet.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.travix.toughjet.entity.Flight;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightRepositoryIntegrationTest {

	@Autowired
	private FlightRepository repository;

	@TestConfiguration
	static class FlightRepositoryIntegrationTestConfiguration {

	}

	@Test
	public void whenFindFlightBy_thenReturnFlightList() {

		List<Flight> result = repository.findFlightBy("AMS", "SAW", LocalDate.of(2020, 03, 01), 2);

		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
}