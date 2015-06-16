package com.senacor.geodata.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by dschmitz on 16/06/15.
 */
@SpringView(name = ErrorView.ERROR_VIEW)
public class ErrorView extends VerticalLayout implements View {
    public static final String ERROR_VIEW = "ERROR_VIEW";

    @PostConstruct
    public void init() {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
