package com.senacor.geodata.views;

import javax.annotation.PostConstruct;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author dschmitz
 */
@SpringView(name = PlaceholderView.VIEW_NAME)
@Theme("valo")
public class PlaceholderView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "PlaceholderView";
    @PostConstruct
    protected void init() {
        setSizeFull();
        addComponent(new Label("Placeholder - Real content will be added soon"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}