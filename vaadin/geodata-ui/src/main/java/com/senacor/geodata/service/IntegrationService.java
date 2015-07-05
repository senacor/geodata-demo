package com.senacor.geodata.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author dschmitz
 */
@Profile("!mock")
@Component
public @interface IntegrationService {
}
