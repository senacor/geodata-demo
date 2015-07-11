package com.senacor.geodata.service.geoname;

import static java.util.stream.Collectors.toList;

import java.util.List;
import javax.validation.constraints.NotNull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.senacor.geodata.model.City;
import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.model.Zipcode;
import com.senacor.geodata.model.ZipcodeSearchParameter;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.service.IntegrationService;
import com.senacor.geodata.service.mock.MockedGeoDataService;
import org.springframework.web.client.RestTemplate;

/**
 * @author dschmitz
 */

@IntegrationService
public class GeoNamesGeoDataService implements GeoDataService {
    public static final String RETRIEVE_RECENT_EARTHQUAKES =
        "/earthquakesJSON?north={north}&south={south}&east={east}&west={west}&lang=de&username={geonamesuser}";
    public static final String RETRIEVE_CITIES = "/citiesJSON?north={north}&south={south}&east={east}&west={west}&lang=de&username={geonamesuser}";
    public static final String RETRIEVE_ZIPCODES =
        "/findNearbyPostalCodesJSON?postalcode={zipcode}&country={countryCode}&radius={diameter}&username={geonamesuser}";

    private String geonamesUrl = "http://api.geonames.org";

    private String username = "myGeo";

    private MockedGeoDataService mockedGeoDataService = new MockedGeoDataService();

    @Override
    public List<City> findCitiesBy(@NotNull MapPositionBox mapPositionBox) {
        RestTemplate restTemplate = new RestTemplate();

        GeoNames geoNames = restTemplate.getForObject(geonamesUrl + RETRIEVE_CITIES, GeoNames.class, mapPositionBox.getNorth(),
            mapPositionBox.getSouth(), mapPositionBox.getEast(), mapPositionBox.getWest(), username);

        // map to "our" structure
        return geoNames.getGeonames().stream().map(City::fromGeoName).collect(toList());
    }

    @HystrixCommand(
        fallbackMethod = "findRecentEarthquakesWithinMock",
        commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")})
    @Override
    public List<Earthquake> findRecentEarthquakesWithin(@NotNull MapPositionBox mapPositionBox) {

        RestTemplate restTemplate = new RestTemplate();

        GeoEarthquakes geoEarthquakes = restTemplate.getForObject(geonamesUrl + RETRIEVE_RECENT_EARTHQUAKES, GeoEarthquakes.class,
            mapPositionBox.getNorth(), mapPositionBox.getSouth(), mapPositionBox.getEast(), mapPositionBox.getWest(), username);

        return geoEarthquakes.getEarthquakes().stream().map(Earthquake::fromGeoEarthquake).collect(toList());
    }

    @HystrixCommand(
        fallbackMethod = "findZipcodesWithMock",
        commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")})
    @Override
    public List<Zipcode> findZipcodes(@NotNull ZipcodeSearchParameter zipcodeSearchParameter) {
        RestTemplate restTemplate = new RestTemplate();

        GeoZipcodes geoZipcodes = restTemplate.getForObject(geonamesUrl + RETRIEVE_ZIPCODES, GeoZipcodes.class, zipcodeSearchParameter.getZipcode(),
            zipcodeSearchParameter.getCountryCode(), zipcodeSearchParameter.getDiameter(), username);

        return geoZipcodes.getPostalCodes().stream().map(Zipcode::fromGeoZipcode).collect(toList());
    }

    public List<Zipcode> findZipcodesWithMock(@NotNull ZipcodeSearchParameter zipcodeSearchParameter) {
        return this.mockedGeoDataService.findZipcodes(zipcodeSearchParameter);
    }

    public List<Earthquake> findRecentEarthquakesWithinMock(@NotNull MapPositionBox mapPositionBox) {
        return this.mockedGeoDataService.findRecentEarthquakesWithin(mapPositionBox);
    }
}
