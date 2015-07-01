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
@SpringView(name = PostalCodeView.VIEW_NAME)
public class PostalCodeView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "PostalCodeView";

    @PostConstruct
    protected void init() {
        addComponent(buildHeaderLabel("Postal Code"));

        addComponent(new Label("This page illustrates using complex components and tables.",
                ContentMode.HTML));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
