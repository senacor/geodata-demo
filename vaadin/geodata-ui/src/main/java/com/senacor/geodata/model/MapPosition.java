package com.senacor.geodata.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Bean representing a map position using lat and long.
 *
 * @author dschmitz
 */
public class MapPosition implements Serializable {
    private final double latitute;
    private final double longitude;

    public MapPosition(double latitute, double longitude) {
        this.latitute = latitute;
        this.longitude = longitude;
    }

    public double getLatitute() {
        return latitute;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "MapPosition{" +
                "latitute=" + latitute +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MapPosition)) {
            return false;
        }

        MapPosition other = (MapPosition) obj;
        return new EqualsBuilder()
                .append(this.longitude, other.longitude)
                .append(this.latitute, other.latitute)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getLatitute()).append(getLongitude()).toHashCode();
    }
}
