package com.senacor.geodata.views;

import com.senacor.geodata.views.components.MenuBar;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author dschmitz
 */
@SpringView(name = GeoDataPortalView.VIEW_NAME)
public class GeoDataPortalView extends HorizontalLayout implements View {
    public static final String VIEW_NAME = "GEODATAPORTALVIEW";
    private SpringViewProvider viewProvider;

    @Autowired
    public GeoDataPortalView(SpringViewProvider viewProvider) {
        super();
        this.viewProvider = viewProvider;
        initGui();
    }

    @PostConstruct
    public void init() {
        initGui();
    }

    private void initGui() {
        Responsive.makeResponsive(this);
        setSizeFull();
        addComponent(buildMenubar());

        Component inner = buildInnerView();
        addComponent(inner);

        setExpandRatio(inner, 1.0f);
    }

    private Component buildInnerView() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addStyleName("geodata-inner-view");

        CssLayout component = buildOverview();
        layout.addComponent(component);
        layout.setExpandRatio(component, 1.0f);

        Navigator navigator = new Navigator(UI.getCurrent(), component);
        navigator.addProvider(this.viewProvider);
        return layout;
    }

    private CssLayout buildOverview() {
        CssLayout layout = new CssLayout();
        layout.setSizeFull();
        layout.addStyleName("geodata-inner-contents");

        layout.addComponent(new Welcome());
        return layout;
    }

    private Component buildMenubar() {
        return new MenuBar();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Notification.show("Welcome", "Login successful - enjoy geoData", Notification.Type.HUMANIZED_MESSAGE);
    }
}
