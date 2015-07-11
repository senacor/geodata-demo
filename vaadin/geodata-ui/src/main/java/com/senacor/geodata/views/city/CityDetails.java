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
        detailsTabsheet.addTab(buildWikipediaDetails(value), "Wikipedia");
        detailsTabsheet.addTab(buildMapView(value), "Map");

        setContent(detailsTabsheet);
    }

    private Component buildMapView(City value) {
        mapBrowser = new BrowserFrame("Browser");
        mapBrowser.setWidth("600px");
        mapBrowser.setHeight("400px");
        return mapBrowser;
    }

    private String buildBingUrl() {
        return String.format("https://www.bing.com/maps/embed/viewer.aspx?v=3&cp=%s~%s&lvl=12&w=600&h=400&sty=r&typ=s&pp=&ps=55&dir=0&mkt=de-de&src=SHELL&form=BMEMJS", this.city.getMapPosition().getLatitute(), this.city.getMapPosition().getLongitude());
    }

    private Component buildWikipediaDetails(City value) {
        wikipediaBrowser = new BrowserFrame("Browser");
        wikipediaBrowser.setWidth("600px");
        wikipediaBrowser.setHeight("400px");

        return wikipediaBrowser;
    }

    private String buildWikipediaUrl() {
        return "http://" + this.wikipedia.getValue().replace("en.wikipedia", "en.m.wikipedia");
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
        City value = (City) event.getProperty().getValue();
        setVisible(true);

        FieldGroup binder = new FieldGroup();
        binder.setItemDataSource(new BeanItem<>(value));
        binder.bindMemberFields(this);

        this.city = value;

        mapBrowser.setSource(new ExternalResource(buildBingUrl()));
        wikipediaBrowser.setSource(new ExternalResource(buildWikipediaUrl()));
    }
}
