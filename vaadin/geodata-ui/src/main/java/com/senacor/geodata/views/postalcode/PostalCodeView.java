package com.senacor.geodata.views.postalcode;

import com.senacor.geodata.views.AbstractCommonView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author dschmitz
 */
@SpringView(name = PostalCodeView.VIEW_NAME)
public class PostalCodeView extends AbstractCommonView {
    public static final String VIEW_NAME = "PostalCodeView";

    @Override
    protected String getHeaderCaption() {
        return "Postal Code";
    }

    @Override
    protected void addContentsTo(VerticalLayout container) {

    }

    @Override
    protected Component buildIntroduction() {
        return new Label("This page illustrates using complex components and tables.",
                ContentMode.HTML);
    }
}
