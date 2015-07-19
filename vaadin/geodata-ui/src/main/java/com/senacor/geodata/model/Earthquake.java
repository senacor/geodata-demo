package com.senacor.geodata.model;

import java.time.LocalDateTime;

/**
 * @author dschmitz
 */
public class Earthquake {
    private final String eqid;
    private final double magnitude;
    private final MapPosition mapPosition;
    private final LocalDateTime dateTime;
    private final double depth;

    public Earthquake(String eqid, double magnitude, MapPosition mapPosition, LocalDateTime dateTime, double depth) {
        this.eqid = eqid;
        this.magnitude = magnitude;
        this.mapPosition = mapPosition;
        this.dateTime = dateTime;
        this.depth = depth;
    }

    public String getEqid() {
        return eqid;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public MapPosition getMapPosition() {
        return mapPosition;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
            "eqid='" + eqid + '\'' +
            ", magnitude=" + magnitude +
            ", mapPosition=" + mapPosition +
            ", dateTime=" + dateTime +
            ", depth=" + depth +
            '}';
    }

}
