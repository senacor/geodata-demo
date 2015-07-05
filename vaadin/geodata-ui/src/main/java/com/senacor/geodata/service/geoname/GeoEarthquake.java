package com.senacor.geodata.service.geoname;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author dschmitz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoEarthquake {
    private String eqid;
    private double magnitude;
    private double lat;
    private double lng;
    private String datetime;
    private double depth;

    public String getEqid() {
        return eqid;
    }

    public void setEqid(String eqid) {
        this.eqid = eqid;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}
