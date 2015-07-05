package com.senacor.geodata.views;

import com.senacor.geodata.model.City;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.components.CityDetails;
import com.senacor.geodata.views.components.CitySearchForm;
import com.senacor.geodata.views.components.CitySearchResultsTable;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
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
    private Window detailsWindow;
    private CityDetails details;

    @Autowired
    public CitySearchView(GeoDataService geoDataService) {
        this.geoDataService = geoDataService;
    }

    @PostConstruct
    protected void init() {
        setSizeUndefined();
        setWidth(100, Unit.PERCENTAGE);
        setSpacing(true);

        addComponent(buildHeaderLabel("City Search"));

        addComponent(new Label("This page demonstrates basic forms, validation and binding.", ContentMode.HTML));

        buildForm();
    }

    private void buildForm() {
        final VerticalLayout container = new VerticalLayout();
        container.setHeightUndefined();
        container.setWidth(100, Unit.PERCENTAGE);
        container.setSpacing(true);

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

        addComponent(container);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}