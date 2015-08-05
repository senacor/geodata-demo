package com.senacor.geodata.model;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author dschmitz
 */
public class SphericalCoordinatesBoxTest {
    @Test
    public void around_returns_a_box_using_a_default_offset() {
        MapPositionBox expected = new MapPositionBox(5.5d, 6.5d, 9.5d, 10.5d);

        assertThat(MapPositionBox.around(new SphericalCoordinates(10d, 6d))).isEqualTo(expected);
    }
}