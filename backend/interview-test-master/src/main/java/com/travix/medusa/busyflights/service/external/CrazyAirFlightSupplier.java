package com.travix.medusa.busyflights.service.external;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;


@Component
public class CrazyAirFlightSupplier {

	public CrazyAirResponse[] findFlight(CrazyAirRequest crazyAirRequest) {

		String URI = UriComponentsBuilder.newInstance().scheme("http").host("10.10.10.103").port(8088)
				.pathSegment("flights").queryParam("origin", crazyAirRequest.getOrigin())
				.queryParam("destination", crazyAirRequest.getDestination())
				.queryParam("departureDate", crazyAirRequest.getDepartureDate())
				.queryParam("returnDate", crazyAirRequest.getReturnDate())
				.queryParam("passengerCount", crazyAirRequest.getPassengerCount()).build().toUriString();

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(URI, CrazyAirResponse[].class);

	}
}
