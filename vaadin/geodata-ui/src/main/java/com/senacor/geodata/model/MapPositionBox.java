package com.senacor.geodata.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

import static java.lang.Math.abs;

/**
 * Bean representing the coordinate box outline on a given map.
 *
 * @author dschmitz
 */
public class MapPositionBox implements Serializable {
    @Min(0)
    @Max(180)
    private double north;
    @Min(0)
    @Max(180)
    private double south;
    @Min(0)
    @Max(360)
    private double east;
    @Min(0)
    @Max(360)
    private double west;

    public MapPositionBox(double north, double south, double east, double west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public MapPositionBox() {
        this(0d, 0d, 0d, 0d);
    }

    public double getNorth() {
        return north;
    }

    public void setNorth(double north) {
        this.north = north;
    }

    public double getSouth() {
        return south;
    }

    public void setSouth(double south) {
        this.south = south;
    }

    public double getEast() {
        return east;
    }

    public void setEast(double east) {
        this.east = east;
    }

    public double getWest() {
        return west;
    }

    public void setWest(double west) {
        this.west = west;
    }

    @Override
    public String toString() {
        return "MapPositionBox{" +
                "north=" + north +
                ", south=" + south +
                ", east=" + east +
                ", west=" + west +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        // lazy...
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public static MapPositionBox around(MapPosition pos) {
        // obviously wrong
        double north = pos.getLatitute() - 1.5;
        north = abs(north);

        double south = pos.getLatitute() + 1.5;
        if (south > 180d) {
            south = 180d - (south - 180d);
        }

        // TODO: this should be easier using java?
        double west = pos.getLongitude() - 1.5;
        if (west < 0) {
            west += 360d;
        }
        double east = (pos.getLongitude() + 1.5) % 360;

        return new MapPositionBox(north, south, east, west);
    }
}
