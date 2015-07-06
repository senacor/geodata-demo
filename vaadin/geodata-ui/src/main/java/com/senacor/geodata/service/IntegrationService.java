package com.senacor.geodata.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Annotation used for "real" services"
 *
 * @author dschmitz
 */
@Profile("!mock")
@Component
@Platform(Platform.Systems.GEONAMES)
public @interface IntegrationService {
}
