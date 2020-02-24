package com.travix.medusa.busyflights.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.travix.medusa.busyflights.service.FlightSupplier;
import com.travix.medusa.busyflights.service.adapter.CrazyAirFlightSupplierAdapter;
import com.travix.medusa.busyflights.util.FlightSupplierTypes;

public class FlightSupplierFactoryUnitTest {

    @Test
    public void getSupplierWithValidArgumentShoulReturnValidAdapter() {
        FlightSupplier supplier = FlightSupplierFactory.getSupplier(FlightSupplierTypes.CRAZYAIR);

        assertNotNull(supplier);
        assertEquals(CrazyAirFlightSupplierAdapter.class, supplier.getClass());
    }

    @Test(expected = NullPointerException.class)
    public void getSupplierWithoutValidArgumentShoulThrowsException() {
        FlightSupplierFactory.getSupplier(null);

    }
}