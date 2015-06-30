package com.senacor.geodata.service;

import com.senacor.geodata.model.City;
import com.senacor.geodata.model.MapPosition;
import com.senacor.geodata.model.MapPositionBox;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author dschmitz
 */
@Component // if conflicting vaadin Class component is used, then fallback to @SpringComponent
public class MockedGeoDataService implements GeoDataService {

    private static final List<City> CITIES = Arrays.asList(
            // use real lat long
            new City("Bonn", "Deutschland", new MapPosition(0d, 0d)),
            new City("Hamburg", "Deutschland", new MapPosition(0d, 0d)),
            new City("Berlin", "Deutschland", new MapPosition(0d, 0d)),
            new City("Nürnberg", "Deutschland", new MapPosition(0d, 0d)),
            new City("Kosice", "Slowakei", new MapPosition(0d, 0d)),
            new City("Wien", "Österreich", new MapPosition(0d, 0d)),
            new City("Frankfurt", "Deutschland", new MapPosition(0d, 0d)),
            new City("München", "Deutschland", new MapPosition(0d, 0d)),
            new City("Paris", "Frankreich", new MapPosition(0d, 0d)),
            new City("Barcelona", "Spanien", new MapPosition(0d, 0d)),
            new City("London", "England", new MapPosition(0d, 0d)),
            new City("Boston", "U.S.A.", new MapPosition(0d, 0d))
    );

    @Override
    public List<City> findCitiesBy(@NotNull MapPositionBox mapPositionBox) {
        // we'll create some random wait-time

        waitState();
        return new ArrayList<>(CITIES);
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
