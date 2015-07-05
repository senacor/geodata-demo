package com.senacor.geodata.service;

import com.senacor.geodata.model.City;
import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPositionBox;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Service for gathering map data.
 *
 * @author dschmitz
 */
public interface GeoDataService {
    /**
     * Searches for cities within a certain area on a map.
     *
     * @param mapPositionBox the position box that is used for searching
     * @return a list of all cities within the postion box
     */
    @NotNull
    List<City> findCitiesBy(@NotNull MapPositionBox mapPositionBox);

    @NotNull
    List<Earthquake> findRecentEarthquakesWithin(@NotNull MapPositionBox mapPositionBox);
}
