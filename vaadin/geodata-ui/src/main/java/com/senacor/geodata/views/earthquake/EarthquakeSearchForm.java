package com.senacor.geodata.views.earthquake;

import static com.vaadin.ui.Alignment.MIDDLE_RIGHT;
import static com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION;
import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;
import static com.vaadin.ui.Notification.show;
import static java.lang.Boolean.TRUE;

import java.util.List;
import java.util.WeakHashMap;

import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.components.BasicPrimaryButton;
import com.senacor.geodata.views.components.FormHeaderLabel;
import com.senacor.geodata.views.components.MapPositionBoxForm;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.senacor.geodata.views.events.SearchResultsChangedListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author dschmitz
 */
public class EarthquakeSearchForm extends VerticalLayout {
    private GeoDataService geoDataService;
    private WeakHashMap<SearchResultsChangedListener, Boolean> listeners = new WeakHashMap<>();

    public EarthquakeSearchForm(GeoDataService geoDataService) {
        this.geoDataService = geoDataService;
        init();
    }

    public void init() {
        MapPositionBox mapPositionBox = new MapPositionBox(44.1d, -9.9d, -22.4d, 55.2d);
        MapPositionBoxForm form = new MapPositionBoxForm();

        addComponent(new FormHeaderLabel("Provide map position box parameters"));

        FieldGroup binder = new FieldGroup();
        binder.setItemDataSource(new BeanItem<>(mapPositionBox));
        binder.bindMemberFields(form);

        Button searchQuakes = new BasicPrimaryButton("Search cities");
        searchQuakes.setDisableOnClick(true);
        searchQuakes.addClickListener(event -> {
            try {
                binder.commit();

                UI.getCurrent().access(() -> show("Searching...", "Looking for coordinates " + mapPositionBox, TRAY_NOTIFICATION));

                // TODO: reactive extensions and Vaadin?
                List<Earthquake> earthquakes = this.geoDataService.findRecentEarthquakesWithin(mapPositionBox);
                fireSearchResultsChangedEvent(earthquakes);
            } catch (FieldGroup.CommitException e) {
                show("Searching...", "Cannot look for " + mapPositionBox + "! " + e.getMessage(), WARNING_MESSAGE);
            }
            searchQuakes.setEnabled(true);
        });

        setSizeUndefined();
        addComponent(form);
        addComponent(searchQuakes);
        setComponentAlignment(searchQuakes, MIDDLE_RIGHT);
    }

    private void fireSearchResultsChangedEvent(List<Earthquake> searchResult) {
        this.listeners.keySet().forEach(listener -> listener.onSearchResultsChanged(new SearchResultsChangedEvent(searchResult)));
    }

    public void addSearchResultsChangedListener(SearchResultsChangedListener listener) {
        this.listeners.put(listener, TRUE);
    }
}
