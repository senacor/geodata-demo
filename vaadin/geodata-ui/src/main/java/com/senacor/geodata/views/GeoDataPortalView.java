package com.senacor.geodata.views;

import com.senacor.geodata.service.GeoDataService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by dschmitz on 16/06/15.
 */
@SpringView(name = GeoDataPortalView.VIEW_NAME)
public class GeoDataPortalView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "GEODATAPORTALVIEW";

    private GeoDataService geoDataService;

    @Autowired
    public GeoDataPortalView(GeoDataService geoDataService) {
        super();
        this.geoDataService = geoDataService;
    }

    @PostConstruct
    public void init() {
        setSizeFull();
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        layout.addComponent(buildHeader());

        HorizontalLayout innerContents = new HorizontalLayout();
        innerContents.setSizeFull();
        innerContents.addComponent(buildMenubar());
        innerContents.addComponent(buildOverview());

        layout.addComponent(innerContents);

        addComponent(layout);
    }

    private Component buildOverview() {
        return new Label("Welcome");
    }

    private Component buildMenubar() {
        return new Label("Menubar");
    }

    private Component buildHeader() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.addComponent(buildLogo());

        Component profile = buildMyProfile();
        layout.addComponent(profile);
        layout.setComponentAlignment(profile, Alignment.MIDDLE_RIGHT);

        Component logout = buildLogout();
        layout.addComponent(logout);
        layout.setComponentAlignment(logout, Alignment.MIDDLE_RIGHT);
        return layout;
    }

    private Component buildLogout() {
        Button logout = new Button("Logout", (event) -> {
            Notification.show("You are being logged out...", Notification.Type.TRAY_NOTIFICATION);

            UI.getCurrent().getNavigator().navigateTo(LoginView.VIEW_NAME);
        });
        logout.addStyleName(ValoTheme.BUTTON_TINY);
        return logout;
    }

    private Component buildMyProfile() {
        HorizontalLayout layout = new HorizontalLayout();

        Label label = new Label("User: R. Hotzenplotz");
        label.setIcon(FontAwesome.USER);
        label.addStyleName(ValoTheme.LABEL_TINY);

        layout.addComponent(label);
        return layout;
    }

    private Component buildLogo() {
        //new Image("GeoDataLogo", )
        return new Label("Logo");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Notification.show("Welcome", "Login successful - enjoy GeoData", Notification.Type.HUMANIZED_MESSAGE);
    }
}
