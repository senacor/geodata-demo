package com.senacor.geodata.service;

import com.senacor.geodata.model.SphericalCoordinates;
import com.senacor.geodata.model.SunriseSunsetTime;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Service for collecting meteo data.
 *
 * @author dschmitz
 */
public interface WeatherService {
    /**
     * Fetches sunrise/sunset time for a given date and position.
     *
     * @param date     the date
     * @param position the map position
     * @return the sunrise/sunset info
     */
    SunriseSunsetTime retrieveSunriseSunsetTime(@NotNull SphericalCoordinates position, @NotNull LocalDate date);
}
