package com.senacor.geodata.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Bean representing a map position using lat and long.
 *
 * @author dschmitz
 */
public class SphericalCoordinates implements Serializable {
    @Min(0)
    @Max(180)
    private final double latitute;
    @Min(0)
    @Max(360)
    private final double longitude;

    public SphericalCoordinates(double latitute, double longitude) {
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
        return "SphericalCoordinates{" +
                "latitute=" + latitute +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SphericalCoordinates)) {
            return false;
        }

        SphericalCoordinates other = (SphericalCoordinates) obj;
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
