package com.senacor.geodata.views;

import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.components.CitySearchForm;
import com.senacor.geodata.views.components.CitySearchResultsTable;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static com.senacor.geodata.views.components.ComponentUtil.buildHeaderLabel;

/**
 * @author dschmitz
 */
@SpringView(name = CitySearchView.VIEW_NAME)
public class CitySearchView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "CitySearchView";

    private GeoDataService geoDataService;


    @Autowired
    public CitySearchView(GeoDataService geoDataService) {
        this.geoDataService = geoDataService;
    }


    @PostConstruct
    protected void init() {
        setSizeUndefined();
        setSpacing(true);

        addComponent(buildHeaderLabel("City Search"));

        addComponent(new Label("This page demonstrates basic forms, validation and binding.", ContentMode.HTML));

        buildForm();
    }

    private void buildForm() {
        VerticalLayout container = new VerticalLayout();
        container.setHeightUndefined();
        container.setWidth(100, Unit.PERCENTAGE);
        container.setSpacing(true);

        final CitySearchResultsTable table = new CitySearchResultsTable("");
        table.setVisible(false);

        CitySearchForm citySearchForm = new CitySearchForm(this.geoDataService);
        citySearchForm.addSearchResultsChangedListener(table);

        container.addComponent(citySearchForm);
        container.addComponent(table);

        addComponent(container);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}