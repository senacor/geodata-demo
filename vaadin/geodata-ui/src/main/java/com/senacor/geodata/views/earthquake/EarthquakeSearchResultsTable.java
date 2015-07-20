package com.senacor.geodata.views.earthquake;

import com.senacor.geodata.model.Earthquake;
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
public class EarthquakeSearchResultsTable extends Table implements SearchResultsChangedListener<Earthquake> {
    public EarthquakeSearchResultsTable(String caption) {
        super(caption);

        setSizeUndefined();
        setWidth(100, Unit.PERCENTAGE);
        setPageLength(5);

        BeanContainer<String, Earthquake> beanContainer = new BeanContainer<>(Earthquake.class);
        beanContainer.setBeanIdProperty("eqid");

        setContainerDataSource(beanContainer);
        setColumnHeader("magnitude", "Magnitude");
        setColumnHeader("mapPosition", "Map location");
        setColumnHeader("dateTime", "Date and time");
        setColumnHeader("depth", "Depth");

        setVisibleColumnsOnTable();

        setSelectable(true);
        setNullSelectionAllowed(false);
    }


    @Override
    public void onSearchResultsChanged(SearchResultsChangedEvent<Earthquake> event) {
        setContainerDataSource(new ListContainer<>(event.getSearchResult()));
        setVisibleColumnsOnTable();
        if (event.getSearchResult().isEmpty()) {
            show("City search", "The search returned no results. Try other coordinates", TRAY_NOTIFICATION);
        } else {
            setVisible(true);
        }
    }

    private void setVisibleColumnsOnTable() {
        setVisibleColumns("dateTime", "mapPosition", "magnitude", "depth");
    }
}
