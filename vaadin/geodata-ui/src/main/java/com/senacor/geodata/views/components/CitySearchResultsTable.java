package com.senacor.geodata.views.components;

import com.senacor.geodata.model.City;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.senacor.geodata.views.events.SearchResultsChangedListener;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import org.vaadin.viritin.ListContainer;

import static com.vaadin.ui.Notification.Type.HUMANIZED_MESSAGE;
import static com.vaadin.ui.Notification.show;

/**
 * @author dschmitz
 */
public class CitySearchResultsTable extends Table implements SearchResultsChangedListener {
    public CitySearchResultsTable(String caption) {
        super(caption);

        setSizeUndefined();
        setPageLength(10);
        addValueChangeListener(event -> show("Value Change"));

        BeanContainer<String, City> beanContainer = new BeanContainer<>(City.class);
        beanContainer.setBeanIdProperty("name");

        setContainerDataSource(beanContainer);
        setColumnHeader("name", "City name");
        setColumnHeader("mapPosition", "Map location");
        setColumnHeader("country", "Country");
    }

    @Override
    public void onSearchResultsChanged(SearchResultsChangedEvent event) {
        setContainerDataSource(new ListContainer<>(event.getSearchResult()));

        if (event.getSearchResult().isEmpty()) {
            show("City search", "The search returned no results. Try other coordinates", HUMANIZED_MESSAGE);
        } else {
            setVisible(true);
        }

    }
}
