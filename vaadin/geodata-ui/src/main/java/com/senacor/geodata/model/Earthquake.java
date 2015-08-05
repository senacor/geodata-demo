package com.senacor.geodata.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Bean representing data associated with an earthquake at a specific location.
 *
 * @author dschmitz
 */
public class Earthquake {
    @NotNull
    private final String eqid;
    @Max(10)
    @Min(0)
    private final double magnitude;
    @NotNull
    private final SphericalCoordinates sphericalCoordinates;
    @NotNull
    private final LocalDateTime dateTime;
    @Min(0)
    private final double depth;

    public Earthquake(String eqid, double magnitude, SphericalCoordinates sphericalCoordinates, LocalDateTime dateTime, double depth) {
        this.eqid = eqid;
        this.magnitude = magnitude;
        this.sphericalCoordinates = sphericalCoordinates;
        this.dateTime = dateTime;
        this.depth = depth;
    }

    public String getEqid() {
        return eqid;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public SphericalCoordinates getSphericalCoordinates() {
        return sphericalCoordinates;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getDepth() {
        return depth;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "eqid='" + eqid + '\'' +
                ", magnitude=" + magnitude +
                ", sphericalCoordinates=" + sphericalCoordinates +
                ", dateTime=" + dateTime +
                ", depth=" + depth +
                '}';
    }
}
