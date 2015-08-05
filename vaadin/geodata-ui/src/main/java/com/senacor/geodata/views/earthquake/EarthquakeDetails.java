package com.senacor.geodata.views.earthquake;

import com.senacor.geodata.converter.LocalDateTimeToStringConverter;
import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.presenter.EartquakeSearchPresenter;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.city.CitySearchResultsTable;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author dschmitz
 */
public class EarthquakeDetails extends Panel implements Property.ValueChangeListener {
    private final EartquakeSearchPresenter eartquakeSearchPresenter;
    private final GeoDataService geoDataService;
    private Earthquake earthquake;

    @PropertyId("eqid")
    private TextField eqid;
    @PropertyId("magnitude")
    private TextField magnitude;
    @PropertyId("dateTime")
    private TextField dateTime;
    @PropertyId("depth")
    private TextField depth;
    private final BeanFieldGroup<Earthquake> binder;
    private CitySearchResultsTable citySearchResultsTable;

    public EarthquakeDetails(EartquakeSearchPresenter eartquakeSearchPresenter, GeoDataService geoDataService, Earthquake earthquake) {
        super("Earthquake details");
        this.eartquakeSearchPresenter = eartquakeSearchPresenter;
        this.geoDataService = geoDataService;
        this.earthquake = earthquake;
        binder = new BeanFieldGroup<>(Earthquake.class);

        setIcon(FontAwesome.MAP_MARKER);
        setCaptionAsHtml(true);
        setSizeFull();

        // details
        TabSheet detailsTabsheet = new TabSheet();
        detailsTabsheet.addStyleName(ValoTheme.TABSHEET_EQUAL_WIDTH_TABS);

        detailsTabsheet.addTab(buildDetailsForm(earthquake), "Details");
        detailsTabsheet.addTab(buildCitiesAffected(earthquake), "Cities in area");
//        detailsTabsheet.addTab(buildCitiesAffected(earthquake), "Geo Map");
        detailsTabsheet.addTab(buildDistribution(earthquake), "Magnitude Distribution");

        binder.setItemDataSource(this.earthquake);
        binder.bindMemberFields(this);
        setContent(detailsTabsheet);
    }

    private Component buildDistribution(Earthquake earthquake) {
        // Add a AreaChart
//        AreaChart ac = new AreaChart();
//        ac.setOption("legend", "bottom");
//
//
//        ac.addXAxisLabel("Year");
//        ac.addArea("Expenses");
//        ac.addArea("Sales");
//        ac.add("2004", new double[]{100, 200});
//        ac.add("2005", new double[]{75,100});
//        ac.add("2006", new double[]{32,234});
//        ac.add("2007", new double[]{25,2534});
//        ac.add("2008", new double[]{2343, 12});
//        ac.setSizeFull();
//        return ac;
        return new Label("Distribution");
    }

    private Component buildCitiesAffected(Earthquake earthquake) {
        citySearchResultsTable = new CitySearchResultsTable("");
        return citySearchResultsTable;
    }

    private Component buildDetailsForm(Earthquake earthquake) {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        FormLayout form = new FormLayout();
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        this.eqid = new TextField("Earthquake Id");
        this.eqid.setReadOnly(true);
        this.magnitude = new TextField("Magnitude");
        this.magnitude.setReadOnly(true);
        this.dateTime = new TextField("Date and Time");
        this.dateTime.setReadOnly(true);
        this.dateTime.setConverter(new LocalDateTimeToStringConverter());
        this.depth = new TextField("Depth");
        this.depth.setReadOnly(true);

        form.addComponents(this.eqid, this.dateTime, this.magnitude, this.depth);
        layout.addComponent(form);
        return layout;
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        Earthquake quake = (Earthquake) event.getProperty().getValue();
        setVisible(true);

        this.earthquake = quake;

        // build map bounding box
        MapPositionBox mapPositionBox = MapPositionBox.around(this.earthquake.getSphericalCoordinates());

        this.citySearchResultsTable.onSearchResultsChanged(new SearchResultsChangedEvent<>(this.geoDataService.findCitiesBy(mapPositionBox)));
        binder.setItemDataSource(new BeanItem<>(this.earthquake));
        binder.bindMemberFields(this);
    }
}
