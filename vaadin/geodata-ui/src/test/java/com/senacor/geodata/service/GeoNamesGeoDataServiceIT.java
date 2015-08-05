package com.senacor.geodata.service;

import com.senacor.geodata.TestApplicationContext;
import com.senacor.geodata.model.City;
import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.model.SphericalCoordinates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author dschmitz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplicationContext.class)
@WebAppConfiguration
public class GeoNamesGeoDataServiceIT {
    @Autowired
    @Platform(Platform.Systems.GEONAMES)
    private GeoDataService geoNamesGeoDataService;

    @Test
    public void finding_cities_contains_mexico_city() {
        List<City> cities = this.geoNamesGeoDataService.findCitiesBy(new MapPositionBox(44.1d, -9.9d, -22.4d, 55.2d));

        assertTrue(cities.contains(new City("3530597", "Mexiko-Stadt", "MX", new SphericalCoordinates(19.428472427036d, -99.12766456604d), "en.wikipedia.org/wiki/Mexico_City")));
    }

    @Test
    public void finding_earthquakes_smoketest() {
        List<Earthquake> quakes = this.geoNamesGeoDataService.findRecentEarthquakesWithin(new MapPositionBox(44.1d, -9.9d, -22.4d, 55.2d));

        assertFalse(quakes.isEmpty());
    }
}