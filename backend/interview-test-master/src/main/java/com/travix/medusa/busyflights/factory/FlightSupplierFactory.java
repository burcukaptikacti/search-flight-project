package com.travix.medusa.busyflights.factory;

import com.travix.medusa.busyflights.service.FlightSupplier;
import com.travix.medusa.busyflights.service.adapter.CrazyAirFlightSupplierAdapter;
import com.travix.medusa.busyflights.service.adapter.ToughJetFlightSupplierAdapter;
import com.travix.medusa.busyflights.service.external.CrazyAirFlightSupplier;
import com.travix.medusa.busyflights.service.external.ToughJetFlightSupplier;
import com.travix.medusa.busyflights.util.FlightSupplierTypes;

public class FlightSupplierFactory {
	public static FlightSupplier getSupplier(FlightSupplierTypes supplier) {
		switch (supplier) {
		case CRAZYAIR:
			return new CrazyAirFlightSupplierAdapter(new CrazyAirFlightSupplier());

		case TOUGHJET:
			return new ToughJetFlightSupplierAdapter(new ToughJetFlightSupplier());

		default:
			throw new IllegalArgumentException("Illegal argument for FlightSupplierTypes: " + supplier);
		}
	}
}
