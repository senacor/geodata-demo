package com.senacor.geodata.service.mock;

import com.senacor.geodata.model.City;
import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPosition;
import com.senacor.geodata.model.MapPositionBox;
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

    private static final List<Earthquake> EARTHQUAKES = Arrays.asList(
            new Earthquake(UUID.randomUUID().toString(), 10 * random(), new MapPosition(0d, 0d), LocalDateTime.now(), 100 * random())
    );
    private static int id = 1;
    private static final List<City> CITIES = Arrays.asList(
            // use real lat long
            new City("" + id++, "Bonn", "Deutschland", new MapPosition(0d, 0d), ""),
            new City("" + id++, "Hamburg", "Deutschland", new MapPosition(0d, 0d), ""),
            new City("" + id++, "Berlin", "Deutschland", new MapPosition(0d, 0d), ""),
            new City("" + id++, "Nürnberg", "Deutschland", new MapPosition(0d, 0d), ""),
            new City("" + id++, "Kosice", "Slowakei", new MapPosition(0d, 0d), ""),
            new City("" + id++, "Wien", "Österreich", new MapPosition(0d, 0d), ""),
            new City("" + id++, "Frankfurt", "Deutschland", new MapPosition(0d, 0d), ""),
            new City("" + id++, "München", "Deutschland", new MapPosition(0d, 0d), ""),
            new City("" + id++, "Paris", "Frankreich", new MapPosition(0d, 0d), ""),
            new City("" + id++, "Barcelona", "Spanien", new MapPosition(0d, 0d), ""),
            new City("" + id++, "London", "England", new MapPosition(0d, 0d), ""),
            new City("" + id++, "Boston", "U.S.A.", new MapPosition(0d, 0d), "")
    );

    @Override
    public List<City> findCitiesBy(@NotNull MapPositionBox mapPositionBox) {
        // we'll create some random wait-time
        waitState();
        return new ArrayList<>(CITIES);
    }

    @Override
    public List<Earthquake> findRecentEarthquakesWithin(@NotNull MapPositionBox mapPositionBox) {
        waitState();
        return new ArrayList<>(EARTHQUAKES);
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
