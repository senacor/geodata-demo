package com.senacor.geodata.views;

import com.senacor.geodata.service.GeoDataService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by dschmitz on 16/06/15.
 */
@SpringView(name = GeoDataPortalView.GEO_DATA_PORTAL_VIEW)
public class GeoDataPortalView extends VerticalLayout implements View {
    public static final String GEO_DATA_PORTAL_VIEW = "GEO_DATA_PORTAL_VIEW";

    private GeoDataService geoDataService;

    @Autowired
    public GeoDataPortalView(GeoDataService geoDataService) {
        super();
        this.geoDataService = geoDataService;
    }

    @PostConstruct
    public void init() {
        addComponent(new Label(Arrays.toString(this.geoDataService.doSomethingUseful())));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Notification.show("Welcome", "Login successful - enjoy GeoData", Notification.Type.HUMANIZED_MESSAGE);
    }
}
