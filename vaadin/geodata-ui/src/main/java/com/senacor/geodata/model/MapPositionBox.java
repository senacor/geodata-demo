package com.senacor.geodata.model;

import java.io.Serializable;

/**
 * Bean representing the coordinate box outline on a given map.
 *
 * @author dschmitz
 */
public class MapPositionBox implements Serializable {
    private double north;
    private double south;
    private double east;
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
}
