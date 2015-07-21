package com.senacor.geodata.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author dschmitz
 */
public class CityTest {
    @Test
    public void cities_with_same_name_country_and_mapposition_are_equal() {
        City cityA = new City("anyId", "Duesseldorf", "Country", new MapPosition(0d, 0d), "url");
        City cityB = new City("anyId", "Duesseldorf", "Country", new MapPosition(0d, 0d), "url");

        assertEquals(cityA, cityB);
    }

    @Test
    public void cities_with_different_ids_are_not_equal() {
        City cityA = new City("anyId", "Duesseldorf", "Country", new MapPosition(0d, 0d), "url");
        City cityB = new City("otherId", "Duesseldorf", "Country", new MapPosition(0d, 0d), "url");

        assertNotEquals(cityA, cityB);
    }


    @Test
    public void cities_with_different_names_are_not_equal() {
        City cityA = new City("anyId", "Duesseldorf", "Country", new MapPosition(0d, 0d), "url");
        City cityB = new City("anyId", "Koeln", "Country", new MapPosition(0d, 0d), "url");

        assertNotEquals(cityA, cityB);
    }

    @Test
    public void cities_with_different_countries_are_not_equal() {
        City cityA = new City("anyId", "Duesseldorf", "DE", new MapPosition(0d, 0d), "url");
        City cityB = new City("anyId", "Duesseldorf", "BE", new MapPosition(0d, 0d), "url");

        assertNotEquals(cityA, cityB);
    }

    @Test
    public void the_wikipedia_url_does_not_affect_equality() {
        City cityA = new City("anyId", "Duesseldorf", "DE", new MapPosition(0d, 0d), "http://whatever.com");
        City cityB = new City("anyId", "Duesseldorf", "DE", new MapPosition(0d, 0d), "url");

        assertEquals(cityA, cityB);
    }

    @Test
    public void cities_with_different_mappositions_are_not_equal() {
        City cityA = new City("anyId", "Duesseldorf", "Country", new MapPosition(0d, 0d), "url");
        City cityB = new City("anyId", "Koeln", "Country", new MapPosition(1d, 0d), "url");

        assertNotEquals(cityA, cityB);
    }
    
    @Test
    public void wikipedia_url_uses_the_english_mobile_site() {
        City cityA = new City("anyId", "Duesseldorf", "Country", new MapPosition(0d, 0d), "en.wikipedia.org/duesseldorf");

        assertEquals("http://en.m.wikipedia.org/duesseldorf", cityA.buildWikipediaUrl());
    }


    @Test
    public void map_url_uses_lat_and_lon_positions() {
        City cityA = new City("anyId", "Duesseldorf", "Country", new MapPosition(12d, 50d), "en.wikipedia.org/duesseldorf");

        assertEquals("https://www.bing.com/maps/embed/viewer.aspx?v=3&cp=12.0~50.0&lvl=12&w=600&h=400&sty=r&typ=s&pp=&ps=55&dir=0&mkt=de-de&src=SHELL&form=BMEMJS", cityA.buildBingUrl());
    }
}