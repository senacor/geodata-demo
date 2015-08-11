package com.senacor.geodata.views.city;

import com.senacor.geodata.model.City;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import static com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION;

/**
 * @author dschmitz
 */
public class CityDetails extends Panel implements Property.ValueChangeListener {
    @PropertyId("name")
    private TextField name;
    @PropertyId("country")
    private TextField country;
    @PropertyId("wikipedia")
    private TextField wikipedia;

    private City city;
    private BrowserFrame mapBrowser;
    private BrowserFrame wikipediaBrowser;

    public CityDetails(City value) {
        super("City details");
        setIcon(FontAwesome.MAP_MARKER);
        setCaptionAsHtml(true);
        setSizeFull();

        // details
        TabSheet detailsTabsheet = new TabSheet();
        detailsTabsheet.addStyleName(ValoTheme.TABSHEET_EQUAL_WIDTH_TABS);

        detailsTabsheet.addTab(buildDetailsForm(value), "Details");
        detailsTabsheet.addTab(buildWikipediaDetails(), "Wikipedia");
        detailsTabsheet.addTab(buildMapView(), "Map");

        setContent(detailsTabsheet);
    }

    private Component buildMapView() {
        mapBrowser = new BrowserFrame("Browser");
        mapBrowser.setWidth("600px");
        mapBrowser.setHeight("400px");
        return mapBrowser;
    }

    private Component buildWikipediaDetails() {
        wikipediaBrowser = new BrowserFrame("Browser");
        wikipediaBrowser.setWidth("600px");
        wikipediaBrowser.setHeight("400px");

        return wikipediaBrowser;
    }

    private VerticalLayout buildDetailsForm(City value) {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        this.city = value;

        FormLayout form = new FormLayout();
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        this.name = new TextField("Name");
        this.name.setReadOnly(true);
        this.country = new TextField("Country");
        this.name.setReadOnly(true);
        this.wikipedia = new TextField("Wikipedia");
        this.wikipedia.setReadOnly(true);

        form.addComponents(this.name, this.country, this.wikipedia);
        layout.addComponent(form);
        return layout;
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        this.city = (City) event.getProperty().getValue();
        if (null == this.city) {
            setVisible(false);
            mapBrowser.setSource(null);
            wikipediaBrowser.setSource(null);
            return;
        }
        setVisible(true);

        FieldGroup binder = new FieldGroup();
        binder.setItemDataSource(new BeanItem<>(this.city));
        binder.bindMemberFields(this);

        Notification.show("Loading city info", "Loading map data from bing", TRAY_NOTIFICATION);
        mapBrowser.setSource(new ExternalResource(this.city.buildBingUrl()));

        // TODO: how to replace error pane with "you are offline"
        Notification.show("Loading city info", "Loading city details from wikipedia", TRAY_NOTIFICATION);
        wikipediaBrowser.setSource(new ExternalResource(this.city.buildWikipediaUrl()));
    }
}
