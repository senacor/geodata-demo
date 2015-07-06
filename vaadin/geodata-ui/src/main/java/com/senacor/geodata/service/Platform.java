package com.senacor.geodata.service;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Qualifier used for denoting the varying systems.
 *
 * @author dschmitz
 */
@Qualifier
public @interface Platform {
    Systems value();

    enum Systems {
        MOCK,
        GEONAMES
    }
}
