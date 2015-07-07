package com.senacor.geodata.views;

import com.senacor.geodata.views.components.HeaderLabel;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author dschmitz
 */
public class Welcome extends VerticalLayout {
    public Welcome() {
        setSizeUndefined();
        setSpacing(true);

        addComponent(new HeaderLabel("Welcome to geoData"));

        addComponent(new Label("This application offers services for general geo, map and weather releated "
                + "services.",
                ContentMode.HTML));
    }
}