package com.senacor.geodata.views;

import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.components.MapPositionBoxForm;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.ListContainer;

import javax.annotation.PostConstruct;

import static com.senacor.geodata.views.components.ComponentUtil.*;
import static com.vaadin.ui.Alignment.MIDDLE_RIGHT;
import static com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION;
import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;
import static com.vaadin.ui.Notification.show;

/**
 * @author dschmitz
 */
@SpringView(name = EarthquakeStatisticsView.VIEW_NAME)
public class EarthquakeStatisticsView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "EarthquakeStatisticsView";
    @Autowired
    private GeoDataService geoDataService;

    @PostConstruct
    protected void init() {
        setSizeFull();
        addComponent(buildHeaderLabel("Earthquake Statistics"));
        addComponent(new Label("This page demonstrates graphs and visual patterns.",
                ContentMode.HTML));

        MapPositionBox mapPositionBox = new MapPositionBox(44.1d, -9.9d, -22.4d, 55.2d);
        MapPositionBoxForm form = new MapPositionBoxForm();

        addComponent(buildFormHeader("Provide map position box parameters"));

        FieldGroup binder = new FieldGroup();
        binder.setItemDataSource(new BeanItem<>(mapPositionBox));
        binder.bindMemberFields(form);

        Table results = new Table();
        {
            results.setSizeUndefined();
            results.setWidth(100, Unit.PERCENTAGE);
            results.setPageLength(5);
            results.setVisibleColumns("dateTime", "magnitude", "mapPosition", "depth");

            BeanContainer<String, Earthquake> beanContainer = new BeanContainer<>(Earthquake.class);
            beanContainer.setBeanIdProperty("eqid");

            results.setContainerDataSource(beanContainer);
            results.setColumnHeader("magnitude", "Magnitude");
            results.setColumnHeader("mapPosition", "Map location");
            results.setColumnHeader("dateTime", "Date and time");
            results.setColumnHeader("depth", "Depth");
            // how to omit columns

            results.setSelectable(true);
        }

        Button searchQuakes = buildPrimaryButton("Search earthquakes in ares");
        searchQuakes.setDisableOnClick(true);
        searchQuakes.addClickListener(event -> {
            try {
                binder.commit();

                UI.getCurrent().access(() -> show("Searching...", "Looking for coordinates " + mapPositionBox, TRAY_NOTIFICATION));

                // TODO: reactive extensions and Vaadin?
                results.setContainerDataSource(new ListContainer<>(this.geoDataService.findRecentEarthquakesWithin(mapPositionBox)));
            } catch (FieldGroup.CommitException e) {
                show("Searching...", "Cannot look for " + mapPositionBox + "! " + e.getMessage(),
                        WARNING_MESSAGE);
            }
            searchQuakes.setEnabled(true);
        });

        setSizeUndefined();
        addComponent(form);
        addComponent(searchQuakes);
        setComponentAlignment(searchQuakes, MIDDLE_RIGHT);
        addComponent(results);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}