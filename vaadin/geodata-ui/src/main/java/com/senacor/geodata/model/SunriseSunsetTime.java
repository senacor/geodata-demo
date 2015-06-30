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
    private final MapPosition mapPosition;

    public SunriseSunsetTime(LocalDate date, LocalTime sunrise, LocalTime sunset, MapPosition mapPosition) {
        this.date = date;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.mapPosition = mapPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SunriseSunsetTime)) {
            return false;
        }
        SunriseSunsetTime other = (SunriseSunsetTime) o;
        return new EqualsBuilder().append(this.date, other.date)
                .append(this.sunrise, other.sunrise)
                .append(this.sunset, other.sunset)
                .append(this.mapPosition, other.mapPosition).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.date)
                .append(this.sunrise)
                .append(this.sunset)
                .append(this.mapPosition)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "SunriseSunsetTime{" +
                "date=" + date +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", mapPosition=" + mapPosition +
                '}';
    }
}
