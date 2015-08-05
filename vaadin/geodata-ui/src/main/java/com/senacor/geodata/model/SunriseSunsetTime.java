package com.senacor.geodata.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents the information regarding sunset, sunrise for a given
 * date and map position.
 *
 * @author dschmitz
 */
public class SunriseSunsetTime implements Serializable {
    private final LocalDate date;
    private final LocalTime sunrise;
    private final LocalTime sunset;
    private final SphericalCoordinates sphericalCoordinates;

    public SunriseSunsetTime(LocalDate date, LocalTime sunrise, LocalTime sunset, SphericalCoordinates sphericalCoordinates) {
        this.date = date;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.sphericalCoordinates = sphericalCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "SunriseSunsetTime{" +
                "date=" + date +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", sphericalCoordinates=" + sphericalCoordinates +
                '}';
    }
}
