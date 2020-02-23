package com.travix.toughjet.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.travix.toughjet.domain.FlightRequest;
import com.travix.toughjet.entity.Flight;
import com.travix.toughjet.service.FlightService;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private FlightService service;

	@Test
	public void testfindFlight() throws Exception {

		FlightRequest request = new FlightRequest("SAW", "AMS", LocalDate.of(2020, 03, 01), LocalDate.of(2020, 03, 04),
				3);

		Flight flight = new Flight(1L, 1, "Pegasus", "AMS", "SAW", Instant.now(), Instant.now(), 34.5, 12.5, 2.7, 123);
		List<Flight> result = new ArrayList<Flight>();
		result.add(flight);

		given(service.findFlight(request)).willReturn(result);

		mvc.perform(get("/flights?outboundDate=2020-03-01&to=AMS&from=SAW&numberOfAdults=3&inboundDate=2020-03-04")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}