package com.senacor.geodata.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.Nonnull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Bean representing the coordinate box outline on a given map.
 *
 * @author dschmitz
 */
public class MapPositionBox implements Serializable {
    @Min(-90)
    @Max(90)
    private double north;
    @Min(-90)
    @Max(90)
    private double south;
    @Min(-90)
    @Max(90)
    private double east;
    @Min(-90)
    @Max(90)
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

    /**
     * Calculates an obviously wrong bounding box for a given lat/long.
     *
     * TODO: Instead of this crap here, we should implement something like http://janmatuschek.de/LatitudeLongitudeBoundingCoordinates
     *
     * @param pos the starting position for creating the bounding box
     * @return the bounding box
     */
    public static MapPositionBox around(@Nonnull SphericalCoordinates pos) {
        // obviously wrong
        double north = pos.getLongitude() - 0.5;
        double south = pos.getLongitude() + 0.5;
        double east = pos.getLatitute() - 0.5;
        double west = pos.getLatitute() + 0.5;

        return new MapPositionBox(north, south, east, west);
    }
}
