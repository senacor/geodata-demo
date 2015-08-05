package com.senacor.geodata.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author dschmitz
 */
public class EarthquakeTest {
    
    @Test
    public void earthquakes_with_same_id_mag_position_date_depth_are_considered_equal() {
        LocalDateTime now = LocalDateTime.now();
        Earthquake earthquakeA = new Earthquake("anId", 0d, new SphericalCoordinates(10d, 10d), now, 12d);
        Earthquake earthquakeB = new Earthquake("anId", 0d, new SphericalCoordinates(10d, 10d), now, 12d);

        assertEquals(earthquakeA, earthquakeB);
    }

}