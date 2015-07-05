package com.senacor.geodata.service.geoname;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author dschmitz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoEarthquakes {
    private List<GeoEarthquake> earthquakes;

    public List<GeoEarthquake> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(List<GeoEarthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }
}
