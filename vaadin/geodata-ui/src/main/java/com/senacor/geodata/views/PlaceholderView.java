package com.senacor.geodata.views;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author dschmitz
 */
@SpringView(name = PlaceholderView.VIEW_NAME)
public class PlaceholderView extends AbstractCommonView {
    public static final String VIEW_NAME = "PlaceholderView";

    @Override
    protected String getHeaderCaption() {
        return "Placeholder";
    }

    @Override
    protected void addContentsTo(VerticalLayout container) {

    }

    @Override
    protected Component buildIntroduction() {
        return new Label("Simple placeholder for actual contents");
    }
}
