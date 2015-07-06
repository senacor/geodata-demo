package com.senacor.geodata.views.components;

import com.senacor.geodata.GeoDataUI;
import com.senacor.geodata.views.city.CitySearchView;
import com.senacor.geodata.views.earthquake.EarthquakeStatisticsView;
import com.senacor.geodata.views.postalcode.PostalCodeView;
import com.senacor.geodata.views.weather.WeatherInformationView;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Component that represents a collapsing menu.
 *
 * @author dschmitz
 */
public class MenuBar extends CustomComponent {
    public MenuBar() {
        setPrimaryStyleName(ValoTheme.MENU_ROOT);
        setSizeUndefined();
        setHeight(100, Unit.PERCENTAGE);
        setCompositionRoot(buildMenu());
    }

    private Component buildMenu() {
        CssLayout menuContent = new CssLayout();
        menuContent.setStyleName("geodata-menu");
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.setWidth(null);
        menuContent.setHeight(100, Unit.PERCENTAGE);

        // TODO Styling....add MENU_PART and MENU_ITEM Valo styles
        menuContent.addComponent(addMenuTitle());
        menuContent.addComponent(addMenuItems());

        return menuContent;
    }


    private Component buildHeader() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addStyleName("geodata-header-bar");
        layout.setSizeUndefined();
        layout.setWidth(100, Unit.PERCENTAGE);

        Label label = new Label();// "User: R. Hotzenplotz");
        label.setCaption("User: R. Hotzenplotz");
        label.setIcon(FontAwesome.USER);
        label.addStyleName(ValoTheme.LABEL_TINY);

        layout.addComponents(label, buildLogout());
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        return layout;
    }

    private Component buildLogout() {
        Button logout = new Button("Logout", (event) -> {
            Notification.show("You are being logged out...", Notification.Type.TRAY_NOTIFICATION);
            // evil and stupid...refactor after login is implemented
            ((GeoDataUI) UI.getCurrent()).userLoggedOut();
        });

        logout.setIcon(FontAwesome.SIGN_OUT);
        logout.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        logout.addStyleName("geodata-menu-item");
        logout.addStyleName("geodata-menu-logout");
        return logout;
    }

    private Component buildLogo() {
        Image image = new Image(null, new ThemeResource("img/senacor.jpg"));
        image.setWidth(10, Unit.EM);
        image.setHeightUndefined();
        return image;
    }

    private Component addMenuItems() {
        // das hier muss noch dynamisch gemacht werden
        final VerticalLayout menuItems = new VerticalLayout();
        menuItems.setSpacing(true);

        menuItems.addComponent(createMenuItem("City search", FontAwesome.SEARCH, CitySearchView.VIEW_NAME));

        menuItems.addComponent(createMenuItem("Weather information", FontAwesome.SUN_O, WeatherInformationView.VIEW_NAME));

        menuItems.addComponent(createMenuItem("Postal code", FontAwesome.BUILDING, PostalCodeView.VIEW_NAME));

        menuItems.addComponent(createMenuItem("Earthquake statistics", FontAwesome.BAR_CHART_O, EarthquakeStatisticsView.VIEW_NAME));

        menuItems.addComponent(buildLogout());

        return menuItems;
    }

    private Button createMenuItem(String title, Resource icon, final String targetView) {
        Button button = new Button(title);
        button.setIcon(icon);
        button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        button.addStyleName("geodata-menu-item");
        button.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(targetView));
        return button;
    }

    private Component addMenuTitle() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Label label = new Label("<strong>geoData</strong> - Menu", ContentMode.HTML);
        label.addStyleName("geodata-menu-title");
        layout.addComponent(label);
        return layout;
    }

}
