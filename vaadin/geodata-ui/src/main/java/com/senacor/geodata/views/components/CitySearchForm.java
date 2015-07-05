package com.senacor.geodata.views.components;

import com.senacor.geodata.model.City;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.senacor.geodata.views.events.SearchResultsChangedListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.List;
import java.util.WeakHashMap;

import static com.senacor.geodata.views.components.ComponentUtil.buildFormHeader;
import static com.senacor.geodata.views.components.ComponentUtil.buildPrimaryButton;
import static com.vaadin.ui.Alignment.MIDDLE_RIGHT;
import static com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION;
import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;
import static com.vaadin.ui.Notification.show;
import static java.lang.Boolean.TRUE;

/**
 * Search form for cities.
 *
 * @author dschmitz
 */
public class CitySearchForm extends VerticalLayout {
    private GeoDataService geoDataService;
    private WeakHashMap<SearchResultsChangedListener, Boolean> listeners = new WeakHashMap<>();

    public CitySearchForm(GeoDataService geoDataService) {
        this.geoDataService = geoDataService;
        init();
    }

    public void init() {
        MapPositionBox mapPositionBox = new MapPositionBox(44.1d, -9.9d, -22.4d, 55.2d);
        MapPositionBoxForm form = new MapPositionBoxForm();

        addComponent(buildFormHeader("Provide map position box parameters"));

        FieldGroup binder = new FieldGroup();
        binder.setItemDataSource(new BeanItem<>(mapPositionBox));
        binder.bindMemberFields(form);

        Button searchCity = buildPrimaryButton("Search cities");
        searchCity.setDisableOnClick(true);
        searchCity.addClickListener(event -> {
            try {
                binder.commit();

                UI.getCurrent().access(() ->
                    show("Searching...", "Looking for coordinates " + mapPositionBox, TRAY_NOTIFICATION)
                );

                // TODO: reactive extensions and Vaadin?
                List<City> cities = this.geoDataService.findCitiesBy(mapPositionBox);
                fireSearchResultsChangedEvent(cities);
            } catch (FieldGroup.CommitException e) {
                show("Searching...", "Cannot look for " + mapPositionBox + "! " + e.getMessage(),
                        WARNING_MESSAGE);
            }
            searchCity.setEnabled(true);
        });

        setSizeUndefined();
        addComponent(form);
        addComponent(searchCity);
        setComponentAlignment(searchCity, MIDDLE_RIGHT);
    }

    private void fireSearchResultsChangedEvent(List<City> searchResult) {
        this.listeners.keySet().forEach(listener -> listener.onSearchResultsChanged(new SearchResultsChangedEvent(searchResult)));
    }

    public void addSearchResultsChangedListener(SearchResultsChangedListener listener) {
        this.listeners.put(listener, TRUE);
    }
}
