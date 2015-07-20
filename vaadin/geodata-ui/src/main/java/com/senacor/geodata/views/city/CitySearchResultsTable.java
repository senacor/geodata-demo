package com.senacor.geodata.views.city;

import com.senacor.geodata.model.City;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.senacor.geodata.views.events.SearchResultsChangedListener;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import org.vaadin.viritin.ListContainer;

import static com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION;
import static com.vaadin.ui.Notification.show;

/**
 * @author dschmitz
 */
public class CitySearchResultsTable extends Table implements SearchResultsChangedListener<City> {

    public CitySearchResultsTable(String caption) {
        super(caption);

        setSizeUndefined();
        setWidth(100, Unit.PERCENTAGE);
        setPageLength(5);

        BeanContainer<String, City> beanContainer = new BeanContainer<>(City.class);
        beanContainer.setBeanIdProperty("name");

        setContainerDataSource(beanContainer);
        setColumnHeader("name", "City name");
        setColumnHeader("mapPosition", "Map location");
        setColumnHeader("country", "Country");
        setNullSelectionAllowed(false);

        setVisibleColumnsOnTable();

        setSelectable(true);
    }

    private void setVisibleColumnsOnTable() {
        setVisibleColumns("name", "mapPosition", "country");
    }

    @Override
    public void onSearchResultsChanged(SearchResultsChangedEvent<City> event) {
        setContainerDataSource(new ListContainer<>(event.getSearchResult()));
        setVisibleColumnsOnTable();
        if (event.getSearchResult().isEmpty()) {
            show("City search", "The search returned no results. Try other coordinates", TRAY_NOTIFICATION);
        } else {
            setVisible(true);
        }
    }
}
