package com.senacor.geodata.service.geoname;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;

/**
 * @author dschmitz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoNames {
    private List<GeoName> geonames = new LinkedList<>();
    public List<GeoName> getGeonames() {
        return geonames;
    }

    public void setGeonames(List<GeoName> geonames) {
        this.geonames = geonames;
    }

    @Override
    public String toString() {
        return "GeoNames{" +
                "geonames=" + geonames +
                '}';
    }
}
