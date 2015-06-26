package com.senacor.geodata;

import com.senacor.geodata.views.GeoDataPortalView;
import com.senacor.geodata.views.LoginView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dschmitz on 13/06/15.
 */
@Theme("geodata")
@SpringUI  // (path = "/geodata")
@Title("geoData")
public class GeoDataUI extends UI {
    private SpringViewProvider viewProvider;

    @Autowired
    public GeoDataUI(SpringViewProvider viewProvider) {
        super();
        this.viewProvider = viewProvider;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new LoginView(this));
    }

    public void userLoggedIn() {
        setContent(new GeoDataPortalView(viewProvider));
    }

    public void userLoggedOut() {
        setContent(new LoginView(this));
    }
}
