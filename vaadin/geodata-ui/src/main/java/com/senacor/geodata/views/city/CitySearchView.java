package com.senacor.geodata.views.city;

import com.senacor.geodata.model.City;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.AbstractCommonView;
import com.senacor.geodata.views.components.CitySearchForm;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dschmitz
 */
@SpringView(name = CitySearchView.VIEW_NAME)
public class CitySearchView extends AbstractCommonView {
    public static final String VIEW_NAME = "CitySearchView";

    private GeoDataService geoDataService;
    private Window detailsWindow;
    private CityDetails details;

    @Autowired
    public CitySearchView(GeoDataService geoDataService) {
        this.geoDataService = geoDataService;
    }

    @Override
    protected String getHeaderCaption() {
        return "City Search";
    }

    @Override
    protected void addContentsTo(VerticalLayout container) {
        final CitySearchResultsTable table = new CitySearchResultsTable("");
        table.setVisible(false);

        CitySearchForm citySearchForm = new CitySearchForm(geoDataService);
        citySearchForm.addSearchResultsChangedListener(table);

        table.addValueChangeListener(event -> {
            City value = (City) event.getProperty().getValue();
            if (null != value) {
                if (null == detailsWindow) {
                    detailsWindow = new Window();
                    detailsWindow.setDraggable(true);
                    detailsWindow.setResizable(false);
                    detailsWindow.addCloseListener(closeEvent -> detailsWindow = null);
                    detailsWindow.setSizeUndefined();
                    detailsWindow.center();

                    details = new CityDetails(value);
                    detailsWindow.setContent(details);

                    UI.getCurrent().addWindow(detailsWindow);
                }
                FieldGroup binder = new FieldGroup();
                binder.setItemDataSource(new BeanItem<>(value));
                binder.bindMemberFields(details);
            }
        });

        container.addComponent(citySearchForm);
        container.addComponent(table);
    }

    @Override
    protected Component buildIntroduction() {
        return new Label("This page demonstrates basic forms, validation and binding.", ContentMode.HTML);
    }
}