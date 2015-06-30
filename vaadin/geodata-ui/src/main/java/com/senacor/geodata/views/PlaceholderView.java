package com.senacor.geodata.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

import static com.senacor.geodata.views.components.ComponentUtil.buildHeaderLabel;

/**
 * @author dschmitz
 */
@SpringView(name = PlaceholderView.VIEW_NAME)
public class PlaceholderView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "PlaceholderView";

    @PostConstruct
    protected void init() {
        addComponent(buildHeaderLabel("Placeholder"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
