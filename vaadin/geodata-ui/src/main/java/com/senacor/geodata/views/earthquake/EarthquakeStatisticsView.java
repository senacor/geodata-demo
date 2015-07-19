package com.senacor.geodata.views.earthquake;

import com.senacor.geodata.presenter.EartquakeSearchPresenter;
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
@SpringView(name = EarthquakeStatisticsView.VIEW_NAME)
public class EarthquakeStatisticsView extends AbstractCommonView {
    public static final String VIEW_NAME = "EarthquakeStatisticsView";
    @Autowired
    private EartquakeSearchPresenter earthquakeSearchPresenter;

    @Override
    protected void addContentsTo(VerticalLayout container) {
        EarthquakeSearchForm earthquakeSearchForm = new EarthquakeSearchForm(earthquakeSearchPresenter);

        EarthquakeSearchResultsTable results = new EarthquakeSearchResultsTable("");
        earthquakeSearchPresenter.addSearchResultsChangedListener(results);
        results.setVisible(false);

        container.addComponent(earthquakeSearchForm);
        container.addComponent(results);
    }

    @Override
    protected String getHeaderCaption() {
        return "Earthquake Statistics";
    }

    @Override
    protected Component buildIntroduction() {
        return new Label("This page demonstrates graphs and visual patterns.", ContentMode.HTML);
    }
}