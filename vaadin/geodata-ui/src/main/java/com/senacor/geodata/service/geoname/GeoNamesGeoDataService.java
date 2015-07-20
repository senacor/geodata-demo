package com.senacor.geodata.service.geoname;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.senacor.geodata.model.*;
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
    public static final String RETRIEVE_RECENT_EARTHQUAKES =
            "/earthquakesJSON?north={north}&south={south}&east={east}&west={west}&lang=de&username={geonamesuser}";
    public static final String RETRIEVE_CITIES = "/citiesJSON?north={north}&south={south}&east={east}&west={west}&lang=en&username={geonamesuser}";
    public static final String RETRIEVE_ZIPCODES =
            "/findNearbyPostalCodesJSON?postalcode={zipcode}&country={countryCode}&radius={diameter}&username={geonamesuser}";

    @Value("${geonames.url}")
    private String geonamesUrl; //= "http://api.geonames.org";

    @Value("${geonames.username}")
    private String username; //= "myGeo";

    private MockedGeoDataService mockedGeoDataService = new MockedGeoDataService();

    // TODO: how to notify UI if fallback is used
    @HystrixCommand(
            fallbackMethod = "findCitiesByFallback",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @Override
    public List<City> findCitiesBy(@NotNull MapPositionBox mapPositionBox) {
        RestTemplate restTemplate = new RestTemplate();

        GeoNames geoNames = restTemplate.getForObject(geonamesUrl + RETRIEVE_CITIES, GeoNames.class, mapPositionBox.getNorth(),
                mapPositionBox.getSouth(), mapPositionBox.getEast(), mapPositionBox.getWest(), username);

        // map to "our" structure
        return geoNames.getGeonames().stream().map(GeoName::toCity).collect(toList());
    }

    // TODO: replace with typesafe solution
    @HystrixCommand(
            fallbackMethod = "findRecentEarthquakesWithinFallback",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @Override
    public List<Earthquake> findRecentEarthquakesWithin(@NotNull MapPositionBox mapPositionBox) {

        RestTemplate restTemplate = new RestTemplate();

        GeoEarthquakes geoEarthquakes = restTemplate.getForObject(geonamesUrl + RETRIEVE_RECENT_EARTHQUAKES, GeoEarthquakes.class,
                mapPositionBox.getNorth(), mapPositionBox.getSouth(), mapPositionBox.getEast(), mapPositionBox.getWest(), username);

        return geoEarthquakes.getEarthquakes().stream().map(GeoEarthquake::toEarthquake).collect(toList());
    }

    // this sucks...
    @HystrixCommand(
            fallbackMethod = "findZipcodesWithMock",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @Override
    public List<Zipcode> findZipcodes(@NotNull ZipcodeSearchParameter zipcodeSearchParameter) {
        RestTemplate restTemplate = new RestTemplate();

        GeoZipcodes geoZipcodes = restTemplate.getForObject(geonamesUrl + RETRIEVE_ZIPCODES, GeoZipcodes.class, zipcodeSearchParameter.getZipcode(),
                zipcodeSearchParameter.getCountryCode(), zipcodeSearchParameter.getDiameter(), username);

        return geoZipcodes.getPostalCodes().stream().map(GeoZipcode::toZipcode).collect(toList());
    }

    public List<City> findCitiesByFallback(@NotNull MapPositionBox mapPositionBox) {
        return this.mockedGeoDataService.findCitiesBy(mapPositionBox);
    }

    public List<Zipcode> findZipcodesWithMock(@NotNull ZipcodeSearchParameter zipcodeSearchParameter) {
        return this.mockedGeoDataService.findZipcodes(zipcodeSearchParameter);
    }

    public List<Earthquake> findRecentEarthquakesWithinFallback(@NotNull MapPositionBox mapPositionBox) {
        return this.mockedGeoDataService.findRecentEarthquakesWithin(mapPositionBox);
    }
}
