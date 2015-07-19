package com.senacor.geodata.views.postalcode;

import com.senacor.geodata.presenter.ZipcodeSearchPresenter;
import com.senacor.geodata.views.AbstractCommonView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dschmitz
 */
@SpringView(name = PostalCodeView.VIEW_NAME)
public class PostalCodeView extends AbstractCommonView {
    public static final String VIEW_NAME = "PostalCodeView";

    @Autowired
    private ZipcodeSearchPresenter zipcodeSearchPresenter;

    @Override
    protected String getHeaderCaption() {
        return "Postal Code";
    }

    @Override
    protected void addContentsTo(VerticalLayout container) {
        ZipcodeSearchForm zipcodeSearchForm = new ZipcodeSearchForm(zipcodeSearchPresenter);

        ZipcodeSearchResultsTable results = new ZipcodeSearchResultsTable("");
        results.setVisible(false);

        container.addComponent(zipcodeSearchForm);
        zipcodeSearchPresenter.addSearchResultsChangedListener(results);
        container.addComponent(results);
    }

    @Override
    protected Component buildIntroduction() {
        return new Label("This page illustrates using complex components and tables.", ContentMode.HTML);
    }
}
