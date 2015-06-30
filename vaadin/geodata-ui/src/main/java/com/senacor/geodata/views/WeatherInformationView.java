package com.senacor.geodata.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

import static com.senacor.geodata.views.components.ComponentUtil.buildHeaderLabel;

/**
 * @author dschmitz
 */
@SpringView(name = WeatherInformationView.VIEW_NAME)
public class WeatherInformationView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "WeatherInformationView";

    @PostConstruct
    protected void init() {
        addComponent(buildHeaderLabel("Weather Information"));

        addComponent(new Label("This page demonstrates search, reports, histograms.",
                ContentMode.HTML));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}