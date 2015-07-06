package com.senacor.geodata.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Annotation denominating a mock service
 *
 * @author dschmitz
 */
@Profile("mock")
@Component
@Platform(Platform.Systems.MOCK)
public @interface Mock {
}
