package com.travix.crazyair.controller;

import static org.mockito.BDDMockito.given; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate; import java.util.ArrayList; import java.util.List;

import org.junit.Test; import org.junit.runner.RunWith; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import org.springframework.boot.test.mock.mockito.MockBean; import org.springframework.http.MediaType; import org.springframework.test.context.junit4.SpringRunner; import org.springframework.test.web.servlet.MockMvc;

import com.travix.crazyair.domain.FlightResponse; import com.travix.crazyair.domain.FlightRequest; import com.travix.crazyair.service.FlightService;

@RunWith(SpringRunner.class) @WebMvcTest(FlightController.class) public class FlightControllerIntegrationTest { @Autowired private MockMvc mvc;

@MockBean
private FlightService service;

@Test
public void testfindFlight() throws Exception {

	FlightRequest dto = new FlightRequest("SAW", "AMS", LocalDate.of(2020, 03, 01), LocalDate.of(2020, 03, 04),
			3);
	FlightResponse resultDto = new FlightResponse();
	resultDto.setDepartureAirportCode("SAW");
	resultDto.setDestinationAirportCode("AMS");
	List<FlightResponse> result = new ArrayList<FlightResponse>();
	result.add(resultDto);

	given(service.findFlight(dto)).willReturn(result);

	mvc.perform(get(
			"/flights?departureDate=2020-03-01&destination=AMS&origin=SAW&passengerCount=3&returnDate=2020-03-04")
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
}
}