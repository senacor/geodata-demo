package com.senacor.geodata;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

/**
 * Created by dschmitz on 13/06/15.
 */
@SpringUI
public class GeoDataUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new Label("Welcome to GeoData"));
    }
}
