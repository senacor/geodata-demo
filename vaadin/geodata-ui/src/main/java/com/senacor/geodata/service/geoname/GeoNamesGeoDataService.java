package com.senacor.geodata.service.geoname;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.senacor.geodata.model.City;
import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.service.IntegrationService;
import com.senacor.geodata.service.mock.MockedGeoDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author dschmitz
 */

@IntegrationService
public class GeoNamesGeoDataService implements GeoDataService {
    public static final String RETRIEVE_RECENT_EARTHQUAKES = "/earthquakesJSON?north={north}&south={south}&east={east}&west={west}&lang=de&username={geonamesuser}";
    public static final String RETRIEVE_CITIES = "/citiesJSON?north={north}&south={south}&east={east}&west={west}&lang=de&username={geonamesuser}";

    @Value("${geonames.url}")
    private String geonamesUrl;

    @Value("${geonames.username}")
    private String username;

    private MockedGeoDataService mockedGeoDataService = new MockedGeoDataService();

    @Override
    public List<City> findCitiesBy(@NotNull MapPositionBox mapPositionBox) {
        RestTemplate restTemplate = new RestTemplate();

        GeoNames geoNames = restTemplate.getForObject(geonamesUrl + RETRIEVE_CITIES,
                GeoNames.class, mapPositionBox.getNorth(), mapPositionBox.getSouth(), mapPositionBox.getEast(), mapPositionBox.getWest(), username);

        // map to "our" structure
        return geoNames.getGeonames().stream().map(City::fromGeoName).collect(toList());
    }

    @HystrixCommand(
            fallbackMethod = "findRecentEarthquakesWithinMock",
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    @Override
    public List<Earthquake> findRecentEarthquakesWithin(@NotNull MapPositionBox mapPositionBox) {

        RestTemplate restTemplate = new RestTemplate();

        GeoEarthquakes geoEarthquakes
                = restTemplate.getForObject(geonamesUrl + RETRIEVE_RECENT_EARTHQUAKES,
                GeoEarthquakes.class, mapPositionBox.getNorth(), mapPositionBox.getSouth(), mapPositionBox.getEast(), mapPositionBox.getWest(), username);

        return geoEarthquakes.getEarthquakes().stream().map(Earthquake::fromGeoEarthquake).collect(toList());
    }


    public List<Earthquake> findRecentEarthquakesWithinMock(@NotNull MapPositionBox mapPositionBox) {
        return this.mockedGeoDataService.findRecentEarthquakesWithin(mapPositionBox);
    }
}
