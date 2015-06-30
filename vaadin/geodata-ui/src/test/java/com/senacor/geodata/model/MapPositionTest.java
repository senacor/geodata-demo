package com.senacor.geodata.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author dschmitz
 */
public class MapPositionTest {

    @Test
    public void equals_false_if_lat_differs() {
        assertNotEquals(new MapPosition(1d, 0d), new MapPosition(0d, 0d));
    }

    @Test
    public void equals_false_if_long_differs() {
        assertNotEquals(new MapPosition(0d, 1d), new MapPosition(0d, 0d));
    }

    @Test
    public void equals_true() {
        assertEquals(new MapPosition(0d, 0d), new MapPosition(0d, 0d));
    }
}