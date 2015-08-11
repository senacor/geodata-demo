package com.senacor.geodata.views.city;

import com.senacor.geodata.presenter.CitySearchPresenter;
import com.senacor.geodata.views.AbstractCommonView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A view that provides a means for searching for cities within
 * a map bounding box.
 *
 * @author dschmitz
 */
@SpringView(name = CitySearchView.VIEW_NAME)
public class CitySearchView extends AbstractCommonView {
    public static final String VIEW_NAME = "CitySearchView";

    private CitySearchPresenter citySearchPresenter;

    @Autowired
    public CitySearchView(CitySearchPresenter citySearchPresenter) {
        this.citySearchPresenter = citySearchPresenter;
    }

    @Override
    protected String getHeaderCaption() {
        return "City Search";
    }

    @Override
    protected void addContentsTo(VerticalLayout container) {
        final HorizontalLayout topRow = new HorizontalLayout();
        topRow.setSizeUndefined();
        topRow.setWidth(100, Unit.PERCENTAGE);
        topRow.setSpacing(true);

        final CitySearchResultsTable table = new CitySearchResultsTable("");
        table.setVisible(false);
        final CitySearchForm citySearchForm = new CitySearchForm(citySearchPresenter);
        citySearchPresenter.addSearchResultsChangedListener(table);
        final CityDetails cityDetails = new CityDetails(null);
        cityDetails.setVisible(false);
        table.addValueChangeListener(cityDetails);

        topRow.addComponents(citySearchForm, cityDetails);
        topRow.setExpandRatio(cityDetails, 1f);
        container.addComponent(topRow);
        container.addComponent(table);
    }

    @Override
    protected Component buildIntroduction() {
        return new Label("This page demonstrates basic forms, validation and binding.", ContentMode.HTML);
    }
}