package com.senacor.geodata.service.mock;

import com.senacor.geodata.model.*;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.service.Mock;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.random;

/**
 * @author dschmitz
 */
@Mock
public class MockedGeoDataService implements GeoDataService {
    // use Array generator syntax
    private static final List<Earthquake> EARTHQUAKES = Arrays.asList(new Earthquake(UUID.randomUUID().toString(), 10 * random(), new SphericalCoordinates(
            20 * random(), 20 * random()), LocalDateTime.now(), 100 * random()), new Earthquake(UUID.randomUUID().toString(), 10 * random(),
            new SphericalCoordinates(20 * random(), 20 * random()), LocalDateTime.now(), 100 * random()), new Earthquake(UUID.randomUUID().toString(),
            10 * random(), new SphericalCoordinates(20 * random(), 20 * random()), LocalDateTime.now(), 100 * random()), new Earthquake(
            UUID.randomUUID().toString(), 10 * random(), new SphericalCoordinates(20 * random(), 20 * random()), LocalDateTime.now(), 100 * random()),
        new Earthquake(UUID.randomUUID().toString(), 10 * random(), new SphericalCoordinates(20 * random(), 20 * random()), LocalDateTime.now(),
            100 * random()), new Earthquake(UUID.randomUUID().toString(), 10 * random(), new SphericalCoordinates(20 * random(), 20 * random()),
            LocalDateTime.now(), 100 * random()), new Earthquake(UUID.randomUUID().toString(), 10 * random(), new SphericalCoordinates(20 * random(),
            20 * random()), LocalDateTime.now(), 100 * random()), new Earthquake(UUID.randomUUID().toString(), 10 * random(), new SphericalCoordinates(
            20 * random(), 20 * random()), LocalDateTime.now(), 100 * random()), new Earthquake(UUID.randomUUID().toString(), 10 * random(),
            new SphericalCoordinates(20 * random(), 20 * random()), LocalDateTime.now(), 100 * random()), new Earthquake(UUID.randomUUID().toString(),
            10 * random(), new SphericalCoordinates(20 * random(), 20 * random()), LocalDateTime.now(), 100 * random()));
    private static int id = 1;
    private static final List<City> CITIES = Arrays.asList(
        // use real lat long
        new City("" + id++, "Bonn", "Deutschland", new SphericalCoordinates(0d, 0d), ""), new City("" + id++, "Hamburg", "Deutschland", new SphericalCoordinates(0d,
            0d), ""), new City("" + id++, "Berlin", "Deutschland", new SphericalCoordinates(0d, 0d), ""), new City("" + id++, "Nürnberg", "Deutschland",
            new SphericalCoordinates(0d, 0d), ""), new City("" + id++, "Kosice", "Slowakei", new SphericalCoordinates(0d, 0d), ""), new City("" + id++, "Wien",
            "Österreich", new SphericalCoordinates(0d, 0d), ""), new City("" + id++, "Frankfurt", "Deutschland", new SphericalCoordinates(0d, 0d), ""), new City(
            "" + id++, "München", "Deutschland", new SphericalCoordinates(0d, 0d), ""), new City("" + id++, "Paris", "Frankreich", new SphericalCoordinates(0d, 0d),
            ""), new City("" + id++, "Barcelona", "Spanien", new SphericalCoordinates(0d, 0d), ""), new City("" + id++, "London", "England", new SphericalCoordinates(
            0d, 0d), ""), new City("" + id++, "Boston", "U.S.A.", new SphericalCoordinates(0d, 0d), ""));

    @Override
    public List<City> findCitiesBy(@NotNull MapPositionBox mapPositionBox) {
        return new ArrayList<>(CITIES);
    }

    @Override
    public List<Earthquake> findRecentEarthquakesWithin(@NotNull MapPositionBox mapPositionBox) {
        return new ArrayList<>(EARTHQUAKES);
    }

    @Override
    public List<Zipcode> findZipcodes(@NotNull ZipcodeSearchParameter zipcodeSearchParameter) {
        return Arrays.asList(new Zipcode("Recklinghausen", "45659"));
    }

    private void waitState() {
        long wait = new Random().nextInt(2000);
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            // TODO: add error handler
            throw new RuntimeException(e);
        }
    }
}
