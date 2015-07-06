package com.senacor.geodata.views.weather;

import com.senacor.geodata.views.AbstractCommonView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author dschmitz
 */
@SpringView(name = WeatherInformationView.VIEW_NAME)
public class WeatherInformationView extends AbstractCommonView {
    public static final String VIEW_NAME = "WeatherInformationView";

    @Override
    protected String getHeaderCaption() {
        return "Weather Information";
    }

    @Override
    protected void addContentsTo(VerticalLayout container) {

    }

    @Override
    protected Component buildIntroduction() {
        return new Label("This page demonstrates search, reports, histograms.",
                ContentMode.HTML);
    }
}