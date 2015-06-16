package com.senacor.geodata.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by dschmitz on 14/06/15.
 */
@Component // if conflicting vaadin Class component is used, then fallback to @SpringComponent
public class MockedGeoDataService implements GeoDataService {
    @Override
    public String[] doSomethingUseful() {
        // urgs
        return Arrays.asList("Foo", "bar", "baz").toArray(new String[] {});
    }
}
