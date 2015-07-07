package com.senacor.geodata.views.postalcode;

import static com.vaadin.ui.Alignment.MIDDLE_RIGHT;
import static com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION;
import static com.vaadin.ui.Notification.show;
import static java.lang.Boolean.TRUE;

import java.util.List;
import java.util.WeakHashMap;

import com.senacor.geodata.model.Zipcode;
import com.senacor.geodata.model.ZipcodeSearchParameter;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.components.BasicPrimaryButton;
import com.senacor.geodata.views.components.FormHeaderLabel;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.senacor.geodata.views.events.SearchResultsChangedListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by mblume on 07.07.15.
 */
public class ZipcodeSearchForm extends VerticalLayout {

    private GeoDataService geoDataService;
    private WeakHashMap<SearchResultsChangedListener, Boolean> listeners = new WeakHashMap<>();

    public ZipcodeSearchForm(GeoDataService geoDataService) {
        this.geoDataService = geoDataService;
        init();
    }

    private void init() {
        addComponent(new FormHeaderLabel("Provide zipcode search parameters"));

        ZipcodeSearchParameter searchParameter = new ZipcodeSearchParameter("45659", "DE", 10);

        Button searchZipcodes = new BasicPrimaryButton("Search zipcodes");
        searchZipcodes.setDisableOnClick(true);
        searchZipcodes.addClickListener(event -> {
            UI.getCurrent().access(() -> show("Searching...", "Looking for zipcodes " + searchParameter, TRAY_NOTIFICATION));

            List<Zipcode> zipcodes = geoDataService.findZipcodes(searchParameter);
            fireSearchResultsChangedEvent(zipcodes);
            searchZipcodes.setEnabled(true);
        });

        setSizeUndefined();
        addComponent(searchZipcodes);
        setComponentAlignment(searchZipcodes, MIDDLE_RIGHT);
    }

    private void fireSearchResultsChangedEvent(List<Zipcode> searchResult) {
        this.listeners.keySet().forEach(listener -> listener.onSearchResultsChanged(new SearchResultsChangedEvent(searchResult)));
    }

    public void addSearchResultsChangedListener(SearchResultsChangedListener<Zipcode> listener) {
        this.listeners.put(listener, TRUE);
    }
}
