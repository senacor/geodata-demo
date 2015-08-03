package com.senacor.geodata.model;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author dschmitz
 */
public class MapPositionBoxTest {
    @Test
    public void around_returns_a_box_using_a_default_offset() {
        MapPositionBox expected = new MapPositionBox(8.5d, 11.5d, 7.5d, 4.5d);

        assertThat(MapPositionBox.around(new MapPosition(10d, 6d))).isEqualTo(expected);
    }

    @Test
    public void around_south_pole() {
        MapPositionBox expected = new MapPositionBox(178.5d, 178.5d, 1.5d, 358.5d);

        assertThat(MapPositionBox.around(new MapPosition(180d, 0d))).isEqualTo(expected);
    }

    @Test
    public void around_north_pole() {
        MapPositionBox expected = new MapPositionBox(1.5d, 1.5d, 1.5d, 358.5d);

        assertThat(MapPositionBox.around(new MapPosition(0d, 0d))).isEqualTo(expected);
    }

    @Test
    public void around_with_eastern_overflow() {
        MapPositionBox expected = new MapPositionBox(8.5d, 11.5d, 1.5d, 358.5d);

        assertThat(MapPositionBox.around(new MapPosition(10d, 360d))).isEqualTo(expected);
    }

    @Test
    public void around_with_western_overflow() {
        MapPositionBox expected = new MapPositionBox(8.5d, 11.5d, 1.5d, 358.5d);

        assertThat(MapPositionBox.around(new MapPosition(10d, 0d))).isEqualTo(expected);
    }
}