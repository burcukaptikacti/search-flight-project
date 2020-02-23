package com.travix.medusa.busyflights.service.external;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

@Component
public class ToughJetFlightSupplier {

	public ToughJetResponse[] findFlight(ToughJetRequest toughJetRequest) {
		String URI = UriComponentsBuilder.newInstance().scheme("http").host("10.20.10.103").port(8090)
				.pathSegment("flights").queryParam("from", toughJetRequest.getFrom())
				.queryParam("to", toughJetRequest.getTo()).queryParam("outboundDate", toughJetRequest.getOutboundDate())
				.queryParam("inboundDate", toughJetRequest.getInboundDate())
				.queryParam("numberOfAdults", toughJetRequest.getNumberOfAdults()).build().toUriString();

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(URI, ToughJetResponse[].class);

	}
}
