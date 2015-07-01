package com.senacor.geodata.views;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import static com.senacor.geodata.views.components.ComponentUtil.buildHeaderLabel;

/**
 * @author dschmitz
 */
public class WelcomeView extends VerticalLayout {
    public WelcomeView() {
        setSizeUndefined();
        setSpacing(true);

        addComponent(buildHeaderLabel("Welcome to geoData"));

        addComponent(new Label("This application offers services for general geo, map and weather releated "
                + "services.",
                ContentMode.HTML));
    }


}