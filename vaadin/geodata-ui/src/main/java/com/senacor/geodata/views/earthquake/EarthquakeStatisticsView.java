package com.senacor.geodata.views.earthquake;

import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPosition;
import com.senacor.geodata.presenter.EartquakeSearchPresenter;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.AbstractCommonView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author dschmitz
 */
@SpringView(name = EarthquakeStatisticsView.VIEW_NAME)
public class EarthquakeStatisticsView extends AbstractCommonView {
    public static final String VIEW_NAME = "EarthquakeStatisticsView";
    private final GeoDataService geoDataService;
    private EartquakeSearchPresenter earthquakeSearchPresenter;

    @Autowired
    public EarthquakeStatisticsView(EartquakeSearchPresenter earthquakeSearchPresenter, GeoDataService geoDataService) {
        this.earthquakeSearchPresenter = earthquakeSearchPresenter;
        this.geoDataService = geoDataService;
    }

    @Override
    protected void addContentsTo(VerticalLayout container) {
        final HorizontalLayout topRow = new HorizontalLayout();
        topRow.setSizeUndefined();
        topRow.setWidth(100, Unit.PERCENTAGE);
        topRow.setSpacing(true);

        EarthquakeSearchForm earthquakeSearchForm = new EarthquakeSearchForm(this.earthquakeSearchPresenter);
        EarthquakeSearchResultsTable results = new EarthquakeSearchResultsTable("");
        earthquakeSearchPresenter.addSearchResultsChangedListener(results);
        results.setVisible(false);

        container.addComponent(earthquakeSearchForm);
        container.addComponent(results);

        EarthquakeDetails earthquakeDetails = new EarthquakeDetails(this.earthquakeSearchPresenter, geoDataService, new Earthquake("", 0, new MapPosition(0, 0), LocalDateTime.now(), 0d));
        results.addValueChangeListener(earthquakeDetails);
        earthquakeDetails.setVisible(false);

        topRow.addComponents(earthquakeSearchForm, earthquakeDetails);
        topRow.setExpandRatio(earthquakeDetails, 1f);
        container.addComponent(topRow);
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