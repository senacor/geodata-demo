package com.senacor.geodata.views;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author dschmitz
 */
@SpringView(name = PostalCodeView.VIEW_NAME)
public class PostalCodeView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "PostalCodeView";

    @PostConstruct
    protected void init() {
        setSizeFull();

        addComponent(new Label("..."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
