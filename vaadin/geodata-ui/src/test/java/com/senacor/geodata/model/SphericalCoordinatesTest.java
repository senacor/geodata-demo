package com.senacor.geodata.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author dschmitz
 */
public class SphericalCoordinatesTest {

    @Test
    public void equals_false_if_lat_differs() {
        assertNotEquals(new SphericalCoordinates(1d, 0d), new SphericalCoordinates(0d, 0d));
    }

    @Test
    public void equals_false_if_long_differs() {
        assertNotEquals(new SphericalCoordinates(0d, 1d), new SphericalCoordinates(0d, 0d));
    }

    @Test
    public void equals_true() {
        assertEquals(new SphericalCoordinates(0d, 0d), new SphericalCoordinates(0d, 0d));
    }
}