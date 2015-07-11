package com.senacor.geodata.views.postalcode;

import static com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION;
import static com.vaadin.ui.Notification.show;

import com.senacor.geodata.model.Zipcode;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.senacor.geodata.views.events.SearchResultsChangedListener;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import org.vaadin.viritin.ListContainer;

/**
 * Created by mblume on 07.07.15.
 */
public class ZipcodeSearchResultsTable extends Table implements SearchResultsChangedListener<Zipcode> {

    public ZipcodeSearchResultsTable(String caption) {
        super(caption);

        setSizeUndefined();
        setWidth(100, Unit.PERCENTAGE);
        setPageLength(5);

        BeanContainer<String, Zipcode> beanContainer = new BeanContainer<>(Zipcode.class);
        beanContainer.setBeanIdProperty("code");

        setContainerDataSource(beanContainer);
        setColumnHeader("city", "City");

        setSelectable(true);
    }

    @Override
    public void onSearchResultsChanged(SearchResultsChangedEvent<Zipcode> event) {
        setContainerDataSource(new ListContainer<>(event.getSearchResult()));

        if (event.getSearchResult().isEmpty()) {
            show("Zipcode search", "The search returned no results. Try other search parameters", TRAY_NOTIFICATION);
        } else {
            setVisible(true);
        }
    }
}
